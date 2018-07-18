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

    private static void setFirstPopulation(ExpressionUtils population[]) {
        for(int i = 0; i < POP_SIZE; i++) {
            double x = Math.random() * 10 - 5;
            double y = Math.random() * 10 - 5;

            population[i] = new ExpressionUtils(x, y);
        }
    }

    public static void main(String[] args) {
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
                    buffer[i].mutation();

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
}