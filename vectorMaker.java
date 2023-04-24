import java.util.Random;

public class vectorMaker {
    
    public static void main(String[] args) {
        Random rand = new Random();
        double[] vector1 = new double[3];
        double[] vector2 = new double[3];

        for (int i = 0; i < 3; i++) {
            vector1[i] = rand.nextDouble();
            vector2[i] = rand.nextDouble();
        }

        System.out.println("Vector 1: (" + vector1[0] + ", " + vector1[1] + ", " + vector1[2] + ")");
        System.out.println("Vector 2: (" + vector2[0] + ", " + vector2[1] + ", " + vector2[2] + ")");
    }
}
