package application;

import DAO.acesso.ExercicioDAO;
import DAO.acesso.UsuarioDAO;
import comuns.acesso.ExercicioEscolhido;
import comuns.acesso.Usuario;
import comuns.conteudo.Exercicio;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.*;
import java.util.stream.Collectors;

public class FuncionarioApp {


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

    public static HashMap<Integer, ArrayList<ExercicioEscolhido>> mapExercicioIdExEscolhido(Integer usuarioId){
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

    public static Integer calcTotalExercicios(Integer usuarioId, ArrayList<Integer> exerciciosRealizadosChaves, LinkedHashMap<Integer, Integer> reverseSortedMap, Date dataHoje) {

        HashMap<Integer, Integer> mapExercicioIdQnt = new HashMap<Integer, Integer>();
        HashMap<Integer, ArrayList<ExercicioEscolhido>> mapExercicioId = mapExercicioIdExEscolhido(usuarioId);

        for (Integer i = 1; i <= 17; i++) {
            if (mapExercicioId.containsKey(i)) {
                Integer qtd = 0;
                for (ExercicioEscolhido exercicioEscolhido : mapExercicioId.get(i)) {
                    if (dataHoje == null){
                        qtd += exercicioEscolhido.getQntRealizado();
                        mapExercicioIdQnt.put(i, qtd);
                    } else {
                        if (exercicioEscolhido.getDataExecucao() == dataHoje){
                            qtd += exercicioEscolhido.getQntRealizado();
                            mapExercicioIdQnt.put(i, qtd);
                        }
                    }
                }
            }
        }
        LinkedHashMap<Integer, Integer> mapExercicioQtdOrdenado = sortHashMapByValues(mapExercicioIdQnt);

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
        return calcTotalExercicios(usuarioId,exerciciosRealizadosChaves,reverseSortedMap,null);
    }

    public static Integer calcDuracaoTotal(Integer usuarioId, ArrayList<Integer> exerciciosRealizadosChaves, LinkedHashMap<Integer, Integer> reverseSortedMap, Date dataHoje) {
        HashMap<Integer, ArrayList<ExercicioEscolhido>> mapExercicioId = mapExercicioIdExEscolhido(usuarioId);

        Integer qtd = 0;
        for (Integer i = 1; i <= 17; i++) {
            if (mapExercicioId.containsKey(i)) {
                for (ExercicioEscolhido exercicioEscolhido : mapExercicioId.get(i)) {
                    if (dataHoje == null){
                        qtd += exercicioEscolhido.getDuracao();
                    } else {
                        if (exercicioEscolhido.getDataExecucao() == dataHoje){
                            qtd += exercicioEscolhido.getDuracao();
                        }
                    }
                }
            }
        }
        return qtd;
    }

    public static Integer calcDuracaoTotal(Integer usuarioId, ArrayList<Integer> exerciciosRealizadosChaves, LinkedHashMap<Integer, Integer> reverseSortedMap) {
        return calcDuracaoTotal(usuarioId,exerciciosRealizadosChaves,reverseSortedMap,null);
    }
}
