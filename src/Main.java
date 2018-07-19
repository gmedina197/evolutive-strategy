import java.util.Arrays;

public class Main {

    private static Integer POP_SIZE = 20;
    private static Integer GEN_SIZE = 50;

    private static ExpressionUtils duel(ExpressionUtils a, ExpressionUtils b){
        if (a.fitness > b.fitness){
            return a;
        }else{
            return b;
        }
    }

    private static void onePlusOne(){
        ExpressionUtils population[] = new ExpressionUtils[POP_SIZE];
        ExpressionUtils buffer[] = new ExpressionUtils[POP_SIZE];

        double sumResult = 0.00;
        int cont = 0;

        ExpressionUtils best;

        for(int w = 0; w < 10; w++) {
            best = new ExpressionUtils(0.0,0.0);
            setFirstPopulation(population);

            while (cont != GEN_SIZE) {
                for (int i = 0; i < 20; i++) {
                    int random1 = (int) (Math.random() * 20 % 20);
                    int random2 = (int) (Math.random() * 20 % 20);

                    while (random1 == random2) {
                        random2 = (int) (Math.random() * 20 % 20);
                    }

                    buffer[i] = duel(population[random1], population[random2]);
                    buffer[i].mutation(0.1, 0.1);

                    best = buffer[i].getfitness() > best.getfitness() ? buffer[i] : best;
                }
                for (int i = 0; i < POP_SIZE; i++) {
                    population[i] = new ExpressionUtils(buffer[i].getX(), buffer[i].getY());
                }

                cont++;
            }
            sumResult += best.getfitness();
            System.out.println(best.toString());

            cont = 0;
        }
        System.out.println("Resultado Médio : " + (sumResult) / 10.0);
    }

    public static void MuPlusMu(){
        ExpressionUtils population[] = new ExpressionUtils[20];
        ExpressionUtils buffer[] = new ExpressionUtils[20];
        ExpressionUtils best;
        double sumResult = 0.00;
        int cont;

        for(int w = 0; w < 10; w++) {
            setFirstPopulation(population);
            cont = 0;
            best = new ExpressionUtils(0.0,0.0);

            while(cont != GEN_SIZE){
                double sensibX = 0.1;
                double sensibY = 0.1;

                for(int i = 0; i < POP_SIZE; i++) {
                    buffer[i] = new ExpressionUtils(population[i]);
                    buffer[i].mutation(sensibX, sensibY);

                    if(buffer[i].getfitness() > best.getfitness()) {
                        best = new ExpressionUtils(buffer[i].getX(),buffer[i].getY()) ;
                    }
                }

                Arrays.sort(population);
                Arrays.sort(buffer);
                for(int i = 0; i < 10; i++) {
                    population[i+10] = new ExpressionUtils(buffer[i].getX(),buffer[i].getY()) ;
                }

                cont++;
            }
            sumResult += best.getfitness();
            System.out.println(best.toString());
        }
        System.out.println("Resultado Médio : " + (sumResult) / 10.0);
    }

    private static double[] calcSensibility(ExpressionUtils population[]) {
        double sumX = 0.0;
        double sumY = 0.0;
        double sumQX = 0.0;
        double sumQY = 0.0;
        double[] retorno = new double[2];

        for (ExpressionUtils gen : population) {
            sumX += gen.getX();
            sumY += gen.getY();
            sumQX += Math.pow(gen.getX(), 2);
            sumQY += Math.pow(gen.getY(), 2);
        }
        double varianceX = 1.0/population.length * (sumQX - Math.pow(sumX, 2)/population.length);
        double varianceY = 1.0/population.length * (sumQY - Math.pow(sumY, 2)/population.length);

        retorno[0] = varianceX;
        retorno[1] = varianceY;

        return retorno;
    }

    private static void evolucionaryGoal() {
        ExpressionUtils population[] = new ExpressionUtils[20];
        ExpressionUtils buffer[] = new ExpressionUtils[20];
        ExpressionUtils best;
        double sumResult = 0.00;
        int cont;

        for(int w = 0; w < 10; w++){
            setFirstPopulation(population);
            cont = 0;
            best = new ExpressionUtils(0.0,0.0);
            while (cont != GEN_SIZE){
                double[] sensibs = calcSensibility(population);
                double sensibX = sensibs[0];
                double sensibY = sensibs[1];
                for(int i = 0; i < POP_SIZE; i++) {
                    int indexI = (int) (Math.random()*20 % 20);
                    int indexJ = (int) (Math.random()*20 % 20);

                    while(indexI == indexJ) {
                        indexJ = (int) (Math.random()*20 % 20);
                    }

                    buffer[i] = population[i];
                    buffer[i].mutation(sensibX,sensibY);

                    best = buffer[i].getfitness() > best.getfitness() ? buffer[i] : best;
                }
                Arrays.sort(population);
                Arrays.sort(buffer);
                for(int i = 0; i < 10; i++) {
                    population[i+10] = new ExpressionUtils(buffer[i].getX(),buffer[i].getY()) ;
                }
                cont++;

            };
            sumResult += best.getfitness();
            System.out.println(best.toString());

        }
        System.out.println("Resultado médio: " + (sumResult)/10.0);
    }

    private static void localSearch() {
        ExpressionUtils population[] = new ExpressionUtils[20];
        ExpressionUtils buffer[] = new ExpressionUtils[20];
        ExpressionUtils best;
        double sumResult = 0.00;
        ExpressionUtils buffer2[] = new ExpressionUtils[20];

        int cont = 0;

        setFirstPopulation(population);
        for (int w = 0; w < 10; w++){
            cont = 0;
            best = new ExpressionUtils(0.0,0.0);
            if (w != 0) {
                for (int w2 = 0; w2 < 20; w2++){
                    population[w2] = new ExpressionUtils(buffer2[w2]);
                    population[w2].mutation(1.0, 1.0);
                }
            }
            boolean flag = false;
            while(cont != GEN_SIZE){
                double sensibX = 0.1;
                double sensibY = 0.1;
                for(int i = 0; i < POP_SIZE; i++) {
                    int indexI = (int) (Math.random()*20 % 20);
                    int indexJ = (int) (Math.random()*20 % 20);

                    while(indexI == indexJ) {
                        indexJ = (int) (Math.random()*20 % 20);
                    }

                    buffer[i] = population[i];
                    buffer[i].mutation(sensibX,sensibY);

                    if(buffer[i].getfitness() > best.getfitness()) {
                        best = new ExpressionUtils(buffer[i].getX(),buffer[i].getY()) ;
                        flag = true;
                    }
                }
                Arrays.sort(population);
                Arrays.sort(buffer);
                for(int i = 0; i < 10; i++) {
                    population[i+10] = new ExpressionUtils(buffer[i].getX(),buffer[i].getY()) ;
                }
                cont++;

            }
            if (flag){
                for (int i=0;i<population.length;i++){
                    buffer2[i] = new ExpressionUtils(population[i].getX(),population[i].getY()) ;
                }
            }
            sumResult += best.getfitness();
            System.out.println(best.toString());
        }

        System.out.println("Resultado Médio : " + (sumResult) / 10.0);
    }

    private static void setFirstPopulation(ExpressionUtils population[]) {
        for(int i = 0; i < POP_SIZE; i++) {
            double x = Math.random() * 10 - 5;
            double y = Math.random() * 10 - 5;

            population[i] = new ExpressionUtils(x, y);
        }
    }

    public static void main(String[] args) {
        //onePlusOne();
        //MuPlusMu();
        //evolucionaryGoal();
        localSearch();
    }
}