package application;

import DAO.acesso.EmpresaDAO;
import DAO.acesso.ExercicioDAO;
import DAO.acesso.PremioDAO;
import DAO.acesso.UsuarioDAO;
import comuns.acesso.Empresa;
import comuns.acesso.ExercicioEscolhido;
import comuns.acesso.Premio;
import comuns.acesso.Usuario;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Main;
import sample.PopUpDelete.popUpDeleteController;

import java.io.IOException;
import java.sql.Array;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class EmpresaApp {

    public static LinkedHashMap<Integer, Integer> mapExercicioIdQuantidade(String dataHoje) {
        UsuarioDAO dao = new UsuarioDAO();
        List<Usuario> funcionarioList = dao.listar();
        HashMap<Integer, Integer> mapFuncionarioIdQnt = new HashMap<Integer, Integer>();
        ArrayList<Integer> exerciciosRealizadosChaves = new ArrayList<>();
        LinkedHashMap<Integer, Integer> reverseSortedMap = new LinkedHashMap<>();
        for (Usuario funcionario : funcionarioList) {
            if (dataHoje == null){
                mapFuncionarioIdQnt.put(funcionario.getId(), FuncionarioApp.calcTotalExercicios(funcionario.getId(), exerciciosRealizadosChaves, reverseSortedMap));
            } else {
                mapFuncionarioIdQnt.put(funcionario.getId(), FuncionarioApp.calcTotalExercicios(funcionario.getId(), exerciciosRealizadosChaves, reverseSortedMap, dataHoje));
            }
            exerciciosRealizadosChaves = new ArrayList<>();
            reverseSortedMap = new LinkedHashMap<>();
        }
        LinkedHashMap<Integer, Integer> mapExercicioQtdOrdenado = FuncionarioApp.sortHashMapByValues(mapFuncionarioIdQnt);
        LinkedHashMap<Integer, Integer> mapExercicioQtd = new LinkedHashMap<>();

        mapExercicioQtdOrdenado.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> mapExercicioQtd.put(x.getKey(), x.getValue()));
        return mapExercicioQtd;
    }

    public static HashMap<Integer, String> mapFuncionarioIdNome(LinkedHashMap<Integer, Integer> mapExercicioIdQuantidade){
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        HashMap<Integer, String> mapFuncNome = new HashMap<>();
        for (Usuario usuario : usuarioDAO.listar()) {
            if (mapExercicioIdQuantidade.containsKey(usuario.getId())){
                mapFuncNome.put(usuario.getId(), usuario.getNome());
            }
        }
        return mapFuncNome;
    }

    public static LinkedHashMap<Integer, Integer> mapExercicioIdQuantidade() {
        return mapExercicioIdQuantidade(null);
    }

    public static Integer[] listarUsuariosEmpresa(LinkedHashMap<Integer, Integer> mapExercicioIdQuantidade) {
        return mapExercicioIdQuantidade.keySet().toArray(new Integer[mapExercicioIdQuantidade.keySet().size()]);
    }

    public static Integer[] listarUsuariosEmpresa() {
        LinkedHashMap<Integer, Integer> mapExercicioIdQuantidade = mapExercicioIdQuantidade();
        return mapExercicioIdQuantidade.keySet().toArray(new Integer[mapExercicioIdQuantidade.keySet().size()]);
    }

    public static Integer totalExerciciosTodosFuncionarios(LinkedHashMap<Integer, Integer> mapExercicioIdQuantidade){
        int sum = mapExercicioIdQuantidade.values().stream().reduce(0, Integer::sum);
        return sum;
    }

    public static Integer totalExerciciosTodosFuncionarios(String dataHoje){
        int sum = mapExercicioIdQuantidade(dataHoje).values().stream().reduce(0, Integer::sum);
        return sum;
    }

    public static Integer totalFuncionarios(LinkedHashMap<Integer, Integer> mapExercicioIdQuantidade){
        return listarUsuariosEmpresa(mapExercicioIdQuantidade).length;
    }

    public static Integer totalMinutos(String dataHoje){

        UsuarioDAO dao = new UsuarioDAO();
        List<Usuario> funcionarioList = dao.listar();
        HashMap<Integer, Integer> mapFuncionarioIdQnt = new HashMap<Integer, Integer>();
        ArrayList<Integer> exerciciosRealizadosChaves = new ArrayList<>();
        LinkedHashMap<Integer, Integer> reverseSortedMap = new LinkedHashMap<>();
        for (Usuario funcionario : funcionarioList) {
            if (dataHoje == null){
                mapFuncionarioIdQnt.put(funcionario.getId(), FuncionarioApp.calcDuracaoTotal(funcionario.getId(), exerciciosRealizadosChaves, reverseSortedMap));
            } else {
                mapFuncionarioIdQnt.put(funcionario.getId(), FuncionarioApp.calcDuracaoTotal(funcionario.getId(), exerciciosRealizadosChaves, reverseSortedMap, dataHoje));
            }
            exerciciosRealizadosChaves = new ArrayList<>();
            reverseSortedMap = new LinkedHashMap<>();
        }
        int sum = mapFuncionarioIdQnt.values().stream().reduce(0, Integer::sum);
        return sum;
    }

    public static Integer totalMinutos(){
        return totalMinutos(null);
    }

    public static Integer totalPremios() {
        PremioDAO premioDAO = new PremioDAO();
        return premioDAO.listar().size();
    }

    public static String convertToHours(Integer minutes) {
        return String.valueOf(minutes / 60);
    }

    public static void finalizarPremio(Integer usuarioId) {

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Empresa empresa = usuarioDAO.listarDadosEmpresa();

        PremioDAO premioDAO = new PremioDAO();
        Premio premio = premioDAO.consultar(empresa.getPremioId());
        premio.setUsuarioId(usuarioId);
        premioDAO.alterar(premio);
        finalizarPremioEmpresa();
    }

    public static void finalizarPremioEmpresa() {
        Empresa.getInstance().setNomePremio(null);
        Empresa.getInstance().setPremioId(null);
        Empresa.getInstance().setPossuiPremio(false);
        EmpresaDAO empresaDAO = new EmpresaDAO();
        empresaDAO.alterar(Empresa.getInstance());
    }

    public static ObservableList<PieChart.Data> createData() {
        return FXCollections.observableArrayList(
                new PieChart.Data("realizaram", 50),
                new PieChart.Data("não realizaram", 25));
    }

    public static Chart createChart(){
        ObservableList<PieChart.Data> pieChartData = createData();
        final Chart chart = new Chart(pieChartData);
        pieChartData.forEach(data ->
                data.nameProperty().bind(
                        Bindings.concat(
                                data.pieValueProperty(), " funcionários ", data.getName()
                        )
                )
        );
        return chart;
    public static Integer usuariosFezExercicios(LinkedHashMap<Integer, Integer> usuariosEmpresa) {
        int qtd = 0;
        for (Integer usuarioId : usuariosEmpresa.keySet()) {
            if (usuariosEmpresa.get(usuarioId) != 0){
                 qtd++;
            }
        }
        return qtd;
    }

}
