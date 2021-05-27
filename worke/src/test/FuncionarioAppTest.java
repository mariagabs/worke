package test;

import DAO.acesso.ExercicioDAO;
import DAO.acesso.FuncionarioDAO;
import DAO.acesso.PremioDAO;
import DAO.acesso.UsuarioDAO;
import application.FuncionarioApp;
import application.NotificationApp;
import comuns.acesso.Empresa;
import comuns.acesso.ExercicioEscolhido;
import comuns.acesso.Funcionario;
import comuns.acesso.Premio;
import comuns.conteudo.Exercicio;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.junit.Test;
import sample.Main;

import java.sql.Time;
import java.text.DecimalFormat;
import java.time.LocalTime;
import java.util.*;

public class FuncionarioAppTest {
    private ExercicioDAO exercicioDAO = new ExercicioDAO();
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private Empresa empresa = usuarioDAO.listarDadosEmpresa();
    private FuncionarioDAO daoFunc = new FuncionarioDAO();

    @Test
    public void sortHashMapByValues() {
        HashMap<Integer, Integer> passedMap = FuncionarioApp.calcTotalExerciciosPorExEscolhido(9, null);
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
    }

    @Test
    public void mapExercicioIdExEscolhido() {
        HashMap<Integer, ArrayList<ExercicioEscolhido>> mapExercicioId = new HashMap<Integer, ArrayList<ExercicioEscolhido>>();
        ExercicioDAO exercicioDAO = new ExercicioDAO();
        ArrayList<ExercicioEscolhido> exercicioEscolhidosList = exercicioDAO.listarTotalExercicios(9);
        for (ExercicioEscolhido exercicioEscolhido : exercicioEscolhidosList) {
            if (!mapExercicioId.containsKey(exercicioEscolhido.getExercicioId())) {
                mapExercicioId.put(exercicioEscolhido.getExercicioId(), new ArrayList<ExercicioEscolhido>());
            }
            mapExercicioId.get(exercicioEscolhido.getExercicioId()).add(exercicioEscolhido);
        }
    }

    @Test
    public void calcTotalExercicios() {
        ArrayList<Integer> exerciciosRealizadosChaves = new ArrayList<>();
        LinkedHashMap<Integer, Integer> mapExercicioQtdOrdenado = FuncionarioApp.sortHashMapByValues(FuncionarioApp.calcTotalExerciciosPorExEscolhido(9, null));
        LinkedHashMap<Integer, Integer> reverseSortedMap = new LinkedHashMap<>();
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
    }


    @Test
    public void calcTotalExerciciosPorExEscolhido() {
        HashMap<Integer, Integer> mapExercicioIdQnt = new HashMap<Integer, Integer>();
        HashMap<Integer, ArrayList<ExercicioEscolhido>> mapExercicioId = FuncionarioApp.mapExercicioIdExEscolhido(9);
        String dataHoje = null;
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
    }

    @Test
    public void calcDuracaoTotal() {
        HashMap<Integer, ArrayList<ExercicioEscolhido>> mapExercicioId = FuncionarioApp.mapExercicioIdExEscolhido(9);
        String dataHoje = null;
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
    }

    @Test
    public void exercicioHashMap() {
        List<Exercicio> exercicios = new ArrayList<>();
        HashMap<Integer, Exercicio> exercicioHashMap = new HashMap<>();
        for (Exercicio exercicio : exercicios) {
            exercicioHashMap.put(exercicio.getId(), exercicio);
        }
    }

    @Test
    public void imagemExercicio() {
        Image img;
        Exercicio ex = new Exercicio();
        boolean white = false;

        if (ex.getImagem() != null) {
            if (white) {
                img = new Image(FuncionarioApp.class.getResource("/resources/img/" + ex.getImagem() + "White.png").toExternalForm());
            } else {
                img = new Image(FuncionarioApp.class.getResource("/resources/img/" + ex.getImagem() + "Dark.png").toExternalForm());
            }
        }

    }

    @Test
    public void lblExercicioCor() {
        Color cor;
        Boolean exercicioEscolhido = false;
        if (exercicioEscolhido) {
            cor = Color.WHITE;
        } else {
            cor = Color.valueOf("#0B1F38");
        }
    }

    @Test
    public void openModal() {
        String endereco = null;
        Parent root = null;
        Stage dialog = null;
        if (endereco != null) {
            dialog.getIcons().add(new Image(Main.class.getResourceAsStream(endereco)));
        }

        if (root != null) {
            dialog.setScene(new Scene(root));
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.show();
        }

    }

    @Test
    public void salvaExercicioFeito() {
        Exercicio ex = new Exercicio();
        int qntDisponivel = 0;
        exercicioDAO.exercicioFeito(ex, qntDisponivel);
    }

    @Test
    public void formataIntervaloExercicios() {
        Funcionario func = new Funcionario();
        String selectedInterval = String.valueOf(func.getIntervaloExercicios()).replace(":", "");
        if (selectedInterval.equals("003000")) {
            selectedInterval = selectedInterval.substring(2);
        } else {
            selectedInterval = selectedInterval.substring(1);
        }

    }

    @Test
    public void formataNovoIntervaloExercicios() {
        Funcionario func = new Funcionario();
        String newIntervalo = null;
        if (newIntervalo != null) {
            if (!newIntervalo.equals("3000")) {
                newIntervalo = "0" + newIntervalo;
            } else {
                newIntervalo = "00" + newIntervalo;
            }
            newIntervalo = newIntervalo.substring(0, 2) + ":" + newIntervalo.substring(2, 4) + ":00";
            Time selectedIntervalo = Time.valueOf(newIntervalo);
            func.setIntervaloExercicios(selectedIntervalo);
        }
    }

    @Test
    public void formataDuracaoExercicios() {
        Funcionario func = new Funcionario();
        DecimalFormat df = new DecimalFormat("#");
        String selectedDuracao = df.format(func.getDuracaoExercicios());
    }

    @Test
    public void salvaDuracaoExercicio() {
        String newDuracao = null;
        if (newDuracao != null) {
            Funcionario func = new Funcionario();
            int selectedDuracao = Integer.valueOf(newDuracao);
            func.setDuracaoExercicios(selectedDuracao);
        }
    }

    @Test
    public void listExercicios() {
        Funcionario func = new Funcionario();
        usuarioDAO.consultarExerciciosEscolhidos(usuarioDAO.getLastRotina(func));
    }

    @Test
    public void formataHorario() {
        String horario = null;
        String horarioFormatado = horario;

        if (horario == null) {
            horarioFormatado = "00:00";
        }

    }

    @Test
    public void nomePremio() {
        String nomePremio = "";
        if (empresa.isPossuiPremio()) {
            PremioDAO premioDAO = new PremioDAO();
            Premio premio = premioDAO.consultar(empresa.getPremioId());
            nomePremio = premio.getDescricao();
        }
    }

    @Test
    public void listInstrucoes() {
        Exercicio exercicio = new Exercicio();
        exercicioDAO.listarInstrucoes(exercicio.getId());
    }

    @Test
    public void formatDuracaoExercicioDetails() {
        String duracao;
        Exercicio ex = new Exercicio();
        Funcionario func = new Funcionario();
        Node component = null;
        if (component != null && component.getId() != null && component.getId().contains("nome")) {
            duracao = ex.getNome();
        } else {
            duracao = String.valueOf(func.getDuracaoExercicios()).replace(".0", "") + " min";
        }
    }

    @Test
    public void getLastDateExerciseWasDone() {
        String.valueOf(daoFunc.getLastDateDone());
    }

    @Test
    public void saveRotina() {
        int qntDisponivel = 0;
        exercicioDAO.updateRotina(qntDisponivel);
    }

    @Test
    public void qtdExercisesToDo() {
        Funcionario func = new Funcionario();
        int total = 0;
        if (func.getHoraInicio() != null && func.getHoraTermino() != null) {
            LocalTime horaInicio = LocalTime.parse(func.getHoraInicio());
            LocalTime horaFinal = LocalTime.parse(func.getHoraTermino());
            double horas = (double) ((func.getIntervaloExercicios().getHours() * 60) + func.getIntervaloExercicios().getMinutes()) / 60;

            total = Math.round((int) ((int) (((java.time.Duration.between(horaInicio, horaFinal).toHours()) - 1)) / horas));
        }
    }

    @Test
    public void startNotificationTimer() {
        Funcionario func = new Funcionario();
        if (func.getIntervaloExercicios() != null && func.getIntervaloExercicios().getHours() > 0 && func.getIntervaloExercicios().getMinutes() > 0) {
            double minutos = func.getIntervaloExercicios().getMinutes();
            double horaTotal = func.getIntervaloExercicios().getHours() + (minutos > 0 ? (minutos / 60) : 0);
            NotificationApp.startTimerNotification(horaTotal);
        }
    }

    @Test
    public void setLembreteUsuario() {
        Funcionario func = new Funcionario();
        String lembrete;
        if (func.getLembrete() == null || func.getLembrete().isEmpty()) {
            lembrete = "Clique aqui para adicionar um lembrete!";
        } else {
            lembrete = func.getLembrete();
        }
    }

    @Test
    public void validateExerciciosEscolhidos() {
        List<Exercicio> exercicioList = new ArrayList<>();
        boolean notValid = exercicioList.size() == 0;
    }

    @Test
    public void validateIntervaloExercicios() {
        Time intervalo = new Time(new Date().getTime());
        boolean notValid = intervalo == null;
    }

    @Test
    public void validateDuracaoExercicios() {
        int duracao = 0;
        boolean notValid = duracao == 0;
    }

    @Test
    public void validateHorario() {
        String inicio = "", termino = "";
        boolean notValid = inicio.equals("00:00") || termino.equals("00:00");

    }

    @Test
    public void saveConfig() {
        Funcionario func = new Funcionario();
        int qntDisponivel = 0;
        List<Exercicio> exercicioList = new ArrayList<>();
        boolean equalLists = func.getExercicios().size() == exercicioList.size() && func.getExercicios().containsAll(exercicioList) && exercicioList.containsAll(func.getExercicios());

        if (!equalLists) {
            func.setExercicios(exercicioList);
            exercicioDAO.escolherExercicio(func, qntDisponivel);
        }

        daoFunc.alterarFuncionario(func);
    }


}
