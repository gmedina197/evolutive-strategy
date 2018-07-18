public class ExpressionUtils {
    Double x;
    Double y;
    Double fitness;

    ExpressionUtils(double x, double y) {
        this.x = x;
        this.y = y;

        this.calcFitness();
    }

    public void calcFitness() {
        this.fitness = 0.97 * Math.exp(-(Math.pow((this.x + 3),2) + Math.pow((this.y + 3), 2)) / 5) + 0.98 *
                Math.exp(-(Math.pow((this.x + 3), 2) + Math.pow((this.y - 3), 2)) / 5) + 0.99 *
                Math.exp(-(Math.pow((this.x - 3), 2) + Math.pow((this.y + 3), 2)) / 5) + 1.0 *
                Math.exp(-(Math.pow((this.x - 3), 2) + Math.pow((this.y - 3), 2))/ 5);
        if (this.fitness.equals(Double.NaN)){
            this.fitness = 0.00;
        }
    }

    public void mutation(){
        double s = 0.1;

        double x2 = Math.random() * s * 2 - s;
        double y2 = Math.random() * s * 2 - s;

        x2 += this.x;
        y2 += this.y;

        x2 = x2 > 5 ? 5 : x2;
        y2 = y2 > 5 ? 5 : y2;

        x2 = x2 < 0 ? 0 : x2;
        y2 = y2 < 0 ? 0 : y2;

        this.x = x2;
        this.y = y2;

        this.calcFitness();
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double getfitness() {
        return fitness;
    }

    public void setfitness(Double fitness) {
        this.fitness = fitness;
    }

    @Override
    public String toString() {
        return "<" + this.x + ',' + this.y + ", fitness = " + this.fitness + ">";
    }
}