package application;

import DAO.acesso.ExercicioDAO;
import DAO.acesso.PremioDAO;
import DAO.acesso.UsuarioDAO;
import comuns.acesso.ExercicioEscolhido;
import comuns.acesso.Usuario;

import java.util.*;

public class EmpresaApp {

    public static LinkedHashMap<Integer, Integer> mapExercicioIdQuantidade(Date dataHoje) {
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

    public static LinkedHashMap<Integer, Integer> mapExercicioIdQuantidade() {
        return mapExercicioIdQuantidade(null);
    }


    public static Set<Integer> listarUsuariosEmpresa(LinkedHashMap<Integer, Integer> mapExercicioIdQuantidade) {
        return mapExercicioIdQuantidade.keySet();
    }
    public static Set<Integer> listarUsuariosEmpresa() {
        return mapExercicioIdQuantidade().keySet();
    }

    public static Integer totalExerciciosTodosFuncionarios(LinkedHashMap<Integer, Integer> mapExercicioIdQuantidade){
        int sum = mapExercicioIdQuantidade.values().stream().reduce(0, Integer::sum);
        return sum;
    }

    public static Integer totalFuncionarios(LinkedHashMap<Integer, Integer> mapExercicioIdQuantidade){
        return listarUsuariosEmpresa(mapExercicioIdQuantidade).size();
    }

    public static Integer totalMinutos(Date dataHoje){

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
}
