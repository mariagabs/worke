package test;

import DAO.acesso.EmpresaDAO;
import DAO.acesso.ExercicioDAO;
import DAO.acesso.PremioDAO;
import DAO.acesso.UsuarioDAO;
import application.EmpresaApp;
import application.FuncionarioApp;
import comuns.acesso.Empresa;
import comuns.acesso.Premio;
import comuns.acesso.Usuario;
import comuns.conteudo.Exercicio;
import doughnutChart.Chart;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class EmpresaAppTest {

    @Test
    public void mapExercicioIdQuantidade() {
        String dataHoje = null;
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
    }

    @Test
    public void mapFuncionarioIdNome() {
        LinkedHashMap<Integer, Integer> mapExercicioIdQuantidade = EmpresaApp.mapExercicioIdQuantidade();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        HashMap<Integer, String> mapFuncNome = new HashMap<>();
        for (Usuario usuario : usuarioDAO.listar()) {
            if (mapExercicioIdQuantidade.containsKey(usuario.getId())) {
                mapFuncNome.put(usuario.getId(), usuario.getNome());
            }
        }
    }

    @Test
    public void listarUsuariosEmpresa() {
        LinkedHashMap<Integer, Integer> mapExercicioIdQuantidade = EmpresaApp.mapExercicioIdQuantidade();
        mapExercicioIdQuantidade.keySet().toArray(new Integer[mapExercicioIdQuantidade.keySet().size()]);
    }

    @Test
    public void totalExerciciosTodosFuncionarios() {
        LinkedHashMap<Integer, Integer> mapExercicioIdQuantidade = EmpresaApp.mapExercicioIdQuantidade();
        int sum = mapExercicioIdQuantidade.values().stream().reduce(0, Integer::sum);

        Assert.assertNotEquals(0, sum);
        Assert.assertNotEquals(17, sum);
    }

    @Test
    public void totalFuncionarios() {
        LinkedHashMap<Integer, Integer> mapExercicioIdQuantidade = EmpresaApp.mapExercicioIdQuantidade();
        int length = EmpresaApp.listarUsuariosEmpresa(mapExercicioIdQuantidade).length;
    }

    @Test
    public void totalMinutos() {

        String dataHoje = null;
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
    }

    @Test
    public void totalPremios() {
        PremioDAO premioDAO = new PremioDAO();
        premioDAO.listar().size();
    }

    @Test
    public void convertToHours() {
        Integer minutes = 1;
        double resultado = (double) minutes / 60;
        String resultadoStr = String.format("%.1f", resultado);
    }

    @Test
    public void finalizarPremio() {
        Integer usuarioId = 9;
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Empresa empresa = usuarioDAO.listarDadosEmpresa();

        PremioDAO premioDAO = new PremioDAO();
        Premio premio = premioDAO.consultar(empresa.getPremioId());
        if(premio != null) {
            premio.setUsuarioId(usuarioId);
            premioDAO.alterar(premio);
        }
        finalizarPremioEmpresa();
    }

    @Test
    public void finalizarPremioEmpresa() {
        Empresa.getInstance().setNomePremio(null);
        Empresa.getInstance().setPremioId(null);
        Empresa.getInstance().setPossuiPremio(false);
        EmpresaDAO empresaDAO = new EmpresaDAO();
        empresaDAO.alterar(Empresa.getInstance());
    }

    @Test
    public void listarPremios() {
        PremioDAO premioDAO = new PremioDAO();
        premioDAO.listar();
    }

    @Test
    public void existsDataChartVerificacao() {
        LinkedHashMap<Integer, Integer> usuariosEmpresa = EmpresaApp.mapExercicioIdQuantidade();
        int qtdUsuariosFezExercicio = EmpresaApp.usuariosFezExercicios(usuariosEmpresa);
        int qtdUsuariosNaoFezExercicio = usuariosEmpresa.keySet().size() - qtdUsuariosFezExercicio;

        boolean exists = (qtdUsuariosFezExercicio > 0 && qtdUsuariosNaoFezExercicio > 0);
    }

    @Test
    public void createData() {

        LinkedHashMap<Integer, Integer> usuariosEmpresa = EmpresaApp.mapExercicioIdQuantidade();
        int qtdUsuariosFezExercicio = EmpresaApp.usuariosFezExercicios(usuariosEmpresa);
        int qtdUsuariosNaoFezExercicio = usuariosEmpresa.keySet().size() - qtdUsuariosFezExercicio;

        FXCollections.observableArrayList(
                new PieChart.Data("realizaram", qtdUsuariosFezExercicio),
                new PieChart.Data("não realizaram", qtdUsuariosNaoFezExercicio));
    }

    @Test
    public void createBarChart() {
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
    }

    @Test
    public void usuariosFezExercicios() {
        LinkedHashMap<Integer, Integer> usuariosEmpresa = EmpresaApp.mapExercicioIdQuantidade();
        int qtd = 0;
        for (Integer usuarioId : usuariosEmpresa.keySet()) {
            if (usuariosEmpresa.get(usuarioId) != 0) {
                qtd++;
            }
        }
    }

    @Test
    public void calcTotalExerciciosExEscolhido() {
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
    }

    @Test
    public void consultarUsuarioId() {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.consultarUsuario(9);
    }


}
