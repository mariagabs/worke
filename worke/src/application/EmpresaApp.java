package application;

import DAO.acesso.EmpresaDAO;
import DAO.acesso.ExercicioDAO;
import DAO.acesso.PremioDAO;
import DAO.acesso.UsuarioDAO;
import comuns.acesso.Empresa;
import comuns.acesso.ExercicioEscolhido;
import comuns.acesso.Premio;
import comuns.acesso.Usuario;
import comuns.conteudo.Exercicio;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Main;
import sample.PopUpDelete.popUpDeleteController;

import java.io.IOException;
import java.sql.Array;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

public class EmpresaApp {

    public static LinkedHashMap<Integer, Integer> mapExercicioIdQuantidade(String dataHoje) {
        UsuarioDAO dao = new UsuarioDAO();
        List<Usuario> funcionarioList = dao.listar();
        HashMap<Integer, Integer> mapFuncionarioIdQnt = new HashMap<Integer, Integer>();
        ArrayList<Integer> exerciciosRealizadosChaves = new ArrayList<>();
        LinkedHashMap<Integer, Integer> reverseSortedMap = new LinkedHashMap<>();
        for (Usuario funcionario : funcionarioList) {
            if (dataHoje == null) {
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

    public static HashMap<Integer, String> mapFuncionarioIdNome(LinkedHashMap<Integer, Integer> mapExercicioIdQuantidade) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        HashMap<Integer, String> mapFuncNome = new HashMap<>();
        for (Usuario usuario : usuarioDAO.listar()) {
            if (mapExercicioIdQuantidade.containsKey(usuario.getId())) {
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

    public static Integer totalExerciciosTodosFuncionarios(LinkedHashMap<Integer, Integer> mapExercicioIdQuantidade) {
        int sum = mapExercicioIdQuantidade.values().stream().reduce(0, Integer::sum);
        return sum;
    }

    public static Integer totalExerciciosTodosFuncionarios(String dataHoje) {
        int sum = mapExercicioIdQuantidade(dataHoje).values().stream().reduce(0, Integer::sum);
        return sum;
    }

    public static Integer totalFuncionarios(LinkedHashMap<Integer, Integer> mapExercicioIdQuantidade) {
        return listarUsuariosEmpresa(mapExercicioIdQuantidade).length;
    }

    public static Integer totalMinutos(String dataHoje) {

        UsuarioDAO dao = new UsuarioDAO();
        List<Usuario> funcionarioList = dao.listar();
        HashMap<Integer, Integer> mapFuncionarioIdQnt = new HashMap<Integer, Integer>();
        ArrayList<Integer> exerciciosRealizadosChaves = new ArrayList<>();
        LinkedHashMap<Integer, Integer> reverseSortedMap = new LinkedHashMap<>();
        for (Usuario funcionario : funcionarioList) {
            if (dataHoje == null) {
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

    public static Integer totalMinutos() {
        return totalMinutos(null);
    }

    public static Integer totalPremios() {
        PremioDAO premioDAO = new PremioDAO();
        return premioDAO.listar().size();
    }

    public static String convertToHours(Integer minutes) {
        double resultado = (double) minutes / 60;
        String resultadoStr = String.format("%.1f", resultado);
        return resultadoStr;
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

    public static List<Premio> listarPremios() {
        PremioDAO premioDAO = new PremioDAO();
        return premioDAO.listar();
    }

    public static boolean existsDataChartVerificacao() {
        LinkedHashMap<Integer, Integer> usuariosEmpresa = mapExercicioIdQuantidade();
        int qtdUsuariosFezExercicio = EmpresaApp.usuariosFezExercicios(usuariosEmpresa);
        int qtdUsuariosNaoFezExercicio = usuariosEmpresa.keySet().size() - qtdUsuariosFezExercicio;

        return (qtdUsuariosFezExercicio > 0 && qtdUsuariosNaoFezExercicio > 0);
    }

    public static ObservableList<PieChart.Data> createData() {

        LinkedHashMap<Integer, Integer> usuariosEmpresa = mapExercicioIdQuantidade();
        int qtdUsuariosFezExercicio = EmpresaApp.usuariosFezExercicios(usuariosEmpresa);
        int qtdUsuariosNaoFezExercicio = usuariosEmpresa.keySet().size() - qtdUsuariosFezExercicio;

        return FXCollections.observableArrayList(
                new PieChart.Data("realizaram", qtdUsuariosFezExercicio),
                new PieChart.Data("não realizaram", qtdUsuariosNaoFezExercicio));
    }

    public static Chart createChart() {
        ObservableList<PieChart.Data> pieChartData = createData();
        final Chart chart = new Chart(pieChartData);
        pieChartData.forEach(data ->
                data.nameProperty().bind(
                        Bindings.concat(
                                data.pieValueProperty().intValue(), " funcionários ", data.getName()
                        )
                )
        );
        chart.setLabelsVisible(false);
        return chart;
    }

    public static XYChart.Series createBarChart() {
        ExercicioDAO exercicioDAO = new ExercicioDAO();
        List<Exercicio> exercicioList = exercicioDAO.listar();
        HashMap<Integer, Integer> exercicioIdQntFeita = EmpresaApp.calcTotalExerciciosExEscolhido();
        XYChart.Series<String, Float> series = new XYChart.Series<>();
        int aux = 0;
        int qntMax = exercicioIdQntFeita.size() > 4 ? 4 : exercicioIdQntFeita.size();
        for (Integer id : exercicioIdQntFeita.keySet()) {
            if (aux == qntMax) break;

            Optional<Exercicio> exercicio = exercicioList.stream().filter(ex -> ex.getId() == id).findFirst();
            String nome = exercicio.get().getNome();

            if (nome.contains(" ") && (nome.split(" ")[0].length() + nome.split(" ")[1].length()) >= 10 && nome.split(" ").length > 2) {
                int qnt = (nome.split(" ")[0].length() + nome.split(" ")[1].length());
                nome = nome.split(" ")[0] + " " + nome.split(" ")[1].trim() + "\n" + nome.substring(qnt + 1).trim();
            } else if (nome.contains(" ") && (nome.split(" ")[0].length() > 10)) {
                int qnt = nome.split(" ")[0].length();
                nome = nome.split(" ")[0].trim() + "\n" + nome.substring(qnt).trim();
            }

            Integer qnt = exercicioIdQntFeita.get(id);

            series.getData().add(new XYChart.Data(nome, qnt));
            aux++;
        }

        return series;
    }

    public static Integer usuariosFezExercicios(LinkedHashMap<Integer, Integer> usuariosEmpresa) {
        int qtd = 0;
        for (Integer usuarioId : usuariosEmpresa.keySet()) {
            if (usuariosEmpresa.get(usuarioId) != 0) {
                qtd++;
            }
        }
        return qtd;
    }

    public static HashMap<Integer, Integer> calcTotalExerciciosExEscolhido() {
        HashMap<Integer, Integer> mapFuncionarioIdQnt = new HashMap<Integer, Integer>();
        UsuarioDAO dao = new UsuarioDAO();
        List<Usuario> funcionarioList = dao.listar();
        for (Usuario usuario : funcionarioList) {
            HashMap<Integer, Integer> calcTotalExerciciosPorExEscolhido = FuncionarioApp.calcTotalExerciciosPorExEscolhido(usuario.getId());
            for (Integer exId : calcTotalExerciciosPorExEscolhido.keySet()) {
                if (!mapFuncionarioIdQnt.containsKey(exId)) {
                    mapFuncionarioIdQnt.put(exId, 0);
                }
                int qtd = calcTotalExerciciosPorExEscolhido.get(exId) + mapFuncionarioIdQnt.get(exId);
                mapFuncionarioIdQnt.put(exId, qtd);
            }
        }
        LinkedHashMap<Integer, Integer> mapExercicioQtdOrdenado = FuncionarioApp.sortHashMapByValues(mapFuncionarioIdQnt);
        LinkedHashMap<Integer, Integer> mapExercicioQtd = new LinkedHashMap<>();

        mapExercicioQtdOrdenado.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> mapExercicioQtd.put(x.getKey(), x.getValue()));
        return mapExercicioQtd;
    }

    public static Usuario consultarUsuarioId(int id) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        return usuarioDAO.consultarUsuario(id);
    }

}
