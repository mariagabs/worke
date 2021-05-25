package application;

import DAO.acesso.ExercicioDAO;
import DAO.acesso.FuncionarioDAO;
import DAO.acesso.PremioDAO;
import DAO.acesso.UsuarioDAO;
import DAO.auditoria.AuditoriaTest;
import comuns.acesso.*;
import comuns.conteudo.Exercicio;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Main;

import java.sql.Time;
import java.text.DecimalFormat;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class FuncionarioApp {

    private static ExercicioDAO exercicioDAO = new ExercicioDAO();
    private static UsuarioDAO usuarioDAO = new UsuarioDAO();
    private static Empresa empresa = usuarioDAO.listarDadosEmpresa();
    private static FuncionarioDAO daoFunc = new FuncionarioDAO();

    public static LinkedHashMap<Integer, Integer> sortHashMapByValues(
            HashMap<Integer, Integer> passedMap) {
        List<Integer> mapKeys = new ArrayList<>(passedMap.keySet());
        List<Integer> mapValues = new ArrayList<>(passedMap.values());
        Collections.sort(mapValues);
        Collections.sort(mapKeys);

        LinkedHashMap<Integer, Integer> sortedMap =
                new LinkedHashMap<>();

        Iterator<Integer> valueIt = mapValues.iterator();
        while (valueIt.hasNext()) {
            Integer val = valueIt.next();
            Iterator<Integer> keyIt = mapKeys.iterator();

            while (keyIt.hasNext()) {
                Integer key = keyIt.next();
                Integer comp1 = passedMap.get(key);
                Integer comp2 = val;

                if (comp1.equals(comp2)) {
                    keyIt.remove();
                    sortedMap.put(key, val);
                    break;
                }
            }
        }
        return sortedMap;
    }

    public static HashMap<Integer, ArrayList<ExercicioEscolhido>> mapExercicioIdExEscolhido(Integer usuarioId) {
        HashMap<Integer, ArrayList<ExercicioEscolhido>> mapExercicioId = new HashMap<Integer, ArrayList<ExercicioEscolhido>>();
        ExercicioDAO exercicioDAO = new ExercicioDAO();
        ArrayList<ExercicioEscolhido> exercicioEscolhidosList = exercicioDAO.listarTotalExercicios(usuarioId);
        for (ExercicioEscolhido exercicioEscolhido : exercicioEscolhidosList) {
            if (!mapExercicioId.containsKey(exercicioEscolhido.getExercicioId())) {
                mapExercicioId.put(exercicioEscolhido.getExercicioId(), new ArrayList<ExercicioEscolhido>());
            }
            mapExercicioId.get(exercicioEscolhido.getExercicioId()).add(exercicioEscolhido);
        }
        return mapExercicioId;
    }

    public static Integer calcTotalExercicios(Integer usuarioId, ArrayList<Integer> exerciciosRealizadosChaves, LinkedHashMap<Integer, Integer> reverseSortedMap, String dataHoje) {

        LinkedHashMap<Integer, Integer> mapExercicioQtdOrdenado = sortHashMapByValues(calcTotalExerciciosPorExEscolhido(usuarioId, dataHoje));

        //Use Comparator.reverseOrder() for reverse ordering
        mapExercicioQtdOrdenado.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));


        Integer totalExerciciosRealizados = 0;
        for (Integer i : reverseSortedMap.keySet()) {
            exerciciosRealizadosChaves.add(i);
            if (reverseSortedMap.containsKey(i)) {
                totalExerciciosRealizados += reverseSortedMap.get(i);
            }
        }
        return totalExerciciosRealizados;
    }

    public static Integer calcTotalExercicios(Integer usuarioId, ArrayList<Integer> exerciciosRealizadosChaves, LinkedHashMap<Integer, Integer> reverseSortedMap) {
        return calcTotalExercicios(usuarioId, exerciciosRealizadosChaves, reverseSortedMap, null);
    }

    public static HashMap<Integer, Integer> calcTotalExerciciosPorExEscolhido(Integer usuarioId, String dataHoje) {
        HashMap<Integer, Integer> mapExercicioIdQnt = new HashMap<Integer, Integer>();
        HashMap<Integer, ArrayList<ExercicioEscolhido>> mapExercicioId = mapExercicioIdExEscolhido(usuarioId);

        for (Integer i = 1; i <= 17; i++) {
            if (mapExercicioId.containsKey(i)) {
                Integer qtd = 0;
                for (ExercicioEscolhido exercicioEscolhido : mapExercicioId.get(i)) {
                    if (dataHoje == null) {
                        qtd += exercicioEscolhido.getQntRealizado();
                        mapExercicioIdQnt.put(i, qtd);
                    } else {
                        if (String.valueOf(exercicioEscolhido.getDataExecucao()).equals(dataHoje)) {
                            qtd += exercicioEscolhido.getQntRealizado();
                            mapExercicioIdQnt.put(i, qtd);
                        }
                    }
                }
            }
        }
        return mapExercicioIdQnt;
    }

    public static HashMap<Integer, Integer> calcTotalExerciciosPorExEscolhido(Integer usuarioId) {
        return calcTotalExerciciosPorExEscolhido(usuarioId, null);
    }

    public static Integer calcDuracaoTotal(Integer usuarioId, ArrayList<Integer> exerciciosRealizadosChaves, LinkedHashMap<Integer, Integer> reverseSortedMap, String dataHoje) {
        HashMap<Integer, ArrayList<ExercicioEscolhido>> mapExercicioId = mapExercicioIdExEscolhido(usuarioId);

        Integer qtd = 0;
        for (Integer i = 1; i <= 17; i++) {
            if (mapExercicioId.containsKey(i)) {
                for (ExercicioEscolhido exercicioEscolhido : mapExercicioId.get(i)) {
                    if (dataHoje == null) {
                        qtd += exercicioEscolhido.getDuracao() * exercicioEscolhido.getQntRealizado();
                    } else {
                        if (String.valueOf(exercicioEscolhido.getDataExecucao()).equals(dataHoje)) {
                            qtd += exercicioEscolhido.getDuracao() * exercicioEscolhido.getQntRealizado();
                        }
                    }
                }
            }
        }
        return qtd;
    }

    public static Integer calcDuracaoTotal(Integer usuarioId, ArrayList<Integer> exerciciosRealizadosChaves, LinkedHashMap<Integer, Integer> reverseSortedMap) {
        return calcDuracaoTotal(usuarioId, exerciciosRealizadosChaves, reverseSortedMap, null);
    }

    public static HashMap<Integer, Exercicio> exercicioHashMap(List<Exercicio> exercicios) {
        HashMap<Integer, Exercicio> exercicioHashMap = new HashMap<>();
        for (Exercicio exercicio : exercicios) {
            exercicioHashMap.put(exercicio.getId(), exercicio);
        }

        return exercicioHashMap;
    }

    public static Image imagemExercicio(Boolean white, Exercicio ex) {
        Image img;
        if (white) {
            img = new Image(FuncionarioApp.class.getResource("/resources/img/" + ex.getImagem() + "White.png").toExternalForm());
        } else {
            img = new Image(FuncionarioApp.class.getResource("/resources/img/" + ex.getImagem() + "Dark.png").toExternalForm());
        }

        return img;
    }

    public static Color lblExercicioCor(Boolean exercicioEscolhido){
        Color cor;
        if (exercicioEscolhido) {
            cor = Color.WHITE;
        } else {
            cor = Color.valueOf("#0B1F38");
        }

        return cor;
    }

    public static void openModal(String endereco, Parent root){
        Stage dialog = new Stage();
        dialog.getIcons().add(new Image(Main.class.getResourceAsStream(endereco)));
        dialog.setScene(new Scene(root));
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.show();
    }

    public static void salvaExercicioFeito(Exercicio ex, int qntDisponivel){
        exercicioDAO.exercicioFeito(ex, qntDisponivel);
    }

    public static String formataIntervaloExercicios(Funcionario func){
        String selectedInterval = String.valueOf(func.getIntervaloExercicios()).replace(":", "");
        if (selectedInterval.equals("003000")) {
            selectedInterval = selectedInterval.substring(2);
        } else {
            selectedInterval = selectedInterval.substring(1);
        }

        return selectedInterval;
    }

    public static void formataNovoIntervaloExercicios(String newIntervalo, Funcionario func){

        if (!newIntervalo.equals("3000")) {
            newIntervalo = "0" + newIntervalo;
        } else {
            newIntervalo = "00" + newIntervalo;
        }

        newIntervalo = newIntervalo.substring(0, 2) + ":" + newIntervalo.substring(2, 4) + ":00";
        Time selectedIntervalo = Time.valueOf(newIntervalo);
        func.setIntervaloExercicios(selectedIntervalo);
    }

    public static String formataDuracaoExercicios(Funcionario func){
        DecimalFormat df = new DecimalFormat("#");
        String selectedDuracao = df.format(func.getDuracaoExercicios());
        return selectedDuracao;
    }

    public static void salvaDuracaoExercicio(String newDuracao, Funcionario func){
        int selectedDuracao = Integer.valueOf(newDuracao);
        func.setDuracaoExercicios(selectedDuracao);
    }

    public static List<Exercicio> listExercicios(Funcionario func){
        return usuarioDAO.consultarExerciciosEscolhidos(usuarioDAO.getLastRotina(func));
    }

    public static String formataHorario(String horario){
        String horarioFormatado = horario;

        if (horario == null) {
            horarioFormatado= "00:00";
        }

        return horarioFormatado;

    }

    public static String nomePremio(){
        String nomePremio = "";
        if (empresa.isPossuiPremio()) {
            PremioDAO premioDAO = new PremioDAO();
            Premio premio = premioDAO.consultar(empresa.getPremioId());
            nomePremio = premio.getDescricao();
        }

        return nomePremio;
    }

    public static List<String> listInstrucoes(Exercicio exercicio) {
        return exercicioDAO.listarInstrucoes(exercicio.getId());
    }

    public static String formatDuracaoExercicioDetails(Node component, Exercicio ex, Funcionario func){
        String duracao;
        if (component.getId() != null && component.getId().contains("nome")) {
            duracao = ex.getNome();
        } else {
            duracao = String.valueOf(func.getDuracaoExercicios()).replace(".0", "") + " min";
        }

        return duracao;
    }

    public static String getLastDateExerciseWasDone(){
        return String.valueOf(daoFunc.getLastDateDone());
    }

    public static void saveRotina(int qntDisponivel){
        exercicioDAO.updateRotina(qntDisponivel);
    }

    public static int qtdExercisesToDo(Funcionario func){
        int total = 0;
        if (func.getHoraInicio() != null && func.getHoraTermino() != null) {
            LocalTime horaInicio = LocalTime.parse(func.getHoraInicio());
            LocalTime horaFinal = LocalTime.parse(func.getHoraTermino());
            double horas = (double) ((func.getIntervaloExercicios().getHours() * 60) + func.getIntervaloExercicios().getMinutes()) / 60;

            total = Math.round((int) ((int) (((java.time.Duration.between(horaInicio, horaFinal).toHours()) - 1)) / horas));
        }

        return total;
    }

    public static void startNotificationTimer(Funcionario func){
        if (func.getIntervaloExercicios() != null && func.getIntervaloExercicios().getHours() > 0 && func.getIntervaloExercicios().getMinutes() > 0) {
            double minutos = func.getIntervaloExercicios().getMinutes();
            double horaTotal = func.getIntervaloExercicios().getHours() + (minutos > 0 ? (minutos / 60) : 0);
            NotificationApp.startTimerNotification(horaTotal);
        }
    }

    public static String setLembreteUsuario(Funcionario func){
        String lembrete;
        if (func.getLembrete() == null || func.getLembrete().isEmpty()) {
            lembrete = "Clique aqui para adicionar um lembrete!";
        } else {
            lembrete = func.getLembrete();
        }

        return lembrete;
    }

    public static boolean validateExerciciosEscolhidos(List<Exercicio> exercicioList){
        return exercicioList.size() == 0;
    }

    public static boolean validateIntervaloExercicios(Time intervalo){
        return intervalo == null;
    }

    public static boolean validateDuracaoExercicios(int duracao){
        return duracao == 0;
    }

    public static boolean validateHorario(TextField inicio, TextField termino){
        return inicio.getText().equals("00:00") || termino.getText().equals("00:00");
    }

    public static void saveConfig(Funcionario func, int qntDisponivel, List<Exercicio> exercicioList){
        boolean equalLists = func.getExercicios().size() == exercicioList.size() && func.getExercicios().containsAll(exercicioList) && exercicioList.containsAll(func.getExercicios());

        if (!equalLists) {
            func.setExercicios(exercicioList);
            exercicioDAO.escolherExercicio(func, qntDisponivel);
        }

        daoFunc.alterarFuncionario(func);
    }



}
