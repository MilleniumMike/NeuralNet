import java.util.ArrayList;
import java.util.Scanner;

public class NeuralNetwork {

    public static void hebb() {
        int[][] teacher = {{1, 1, 1, 1, -1, 1, 1, -1, 1, 1, -1, 1, 1, 1, 1}/*0*/, {1, -1, -1, -1, 1, 1, 1, -1, 1, -1, -1, 1, 1, -1, -1}/*1*/, {1, 1, 1, -1, -1, 1, 1, 1, 1, 1, -1, -1, 1, 1, 1}/*2*/, {1, 1, 1, -1, -1, 1, 1, 1, 1, -1, -1, 1, 1, 1, 1}/*3*/, {1, -1, 1, 1, -1, 1, 1, 1, 1, -1, -1, 1, 1, -1, -1}/*4*/, {1, 1, 1, 1, -1, -1, 1, 1, 1, -1, -1, 1, 1, 1, 1}/*5*/}; //inputs to learn
        int[][] teacher_out = {{1, -1, -1, -1, -1, -1},
                {-1, 1, -1, -1, -1, -1},
                {-1, -1, 1, -1, -1, -1},
                {-1, -1, -1, 1, -1, -1},
                {-1, -1, -1, -1, 1, -1},
                {-1, -1, -1, -1, -1, 1}};
        ArrayList<Neuron> neu = new ArrayList<>(); //neurons (can recognize 6 numbers: 0-5)
        for (int i = 0; i < teacher_out[0].length; i++) {
            double[] weight = new double[teacher[0].length];
            for (int j = 0; j < teacher[0].length; j++) {
                weight[j] = Math.random() * 10;
            }
            neu.add(new Neuron(weight, 0, 0)); //initialazing
        }
        for (int i = 0; i < neu.size(); i++) {
            //System.out.println(i);
            boolean redo = true;
            while (redo) {
                teach_one_hebb(teacher, teacher_out[i], neu.get(i));
                for (int j = 0; j < teacher_out[i].length; j++) {
                    if (teacher_out[i][i] == neu.get(i).out(teacher[i])) {
                        redo = false;
                    } else {
                        redo = true;
                        break;
                    }
                }
            }
        }

        //TEST
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        for (int i = 0; i < teacher_out[0].length; i++) {
            if (neu.get(i).out(teacher[number]) == 1) {
                System.out.println("This is " + i);
                break;
            }
        }
    }

    public static void teach_one_hebb(int[][] x, int[] y, Neuron neu) {
        boolean redo = true;
        while (redo) {
            for (int j = 0; j < y.length; j++) {
                if (neu.out(x[j]) == y[j]) {
                    redo = false;
                } else {
                    redo = true;
                    if (neu.out(x[j]) == -1) {
                        for (int i = 0; i < x[j].length; i++) {
                            if (x[j][i] == 1) {
                                neu.add_w(1, i);
                                //System.out.println("+"+neu.out(x[j]));
                            }
                        }
                    } else {
                        for (int i = 0; i < x[j].length; i++) {
                            if (x[j][i] == 1) {
                                neu.add_w(-1, i);
                                //System.out.println("-"+neu.out(x[j]));
                            }
                        }
                    }
                }
            }
        }
    }
}
