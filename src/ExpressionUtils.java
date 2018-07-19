public class ExpressionUtils implements Comparable<ExpressionUtils>{
    Double x;
    Double y;
    Double fitness;

    ExpressionUtils(double x, double y) {
        this.x = x;
        this.y = y;

        this.calcFitness();
    }

    ExpressionUtils(ExpressionUtils clone){
        this.x = clone.x;
        this.y = clone.y;

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

    public void mutation(double sx, double sy){
        double x2 = Math.random() * sx * 2 - sx;
        double y2 = Math.random() * sy * 2 - sy;

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

    @Override
    public int compareTo(ExpressionUtils o) {
        if (this.fitness > o.fitness) {
            return (-1);
        } else {
            if (this.fitness < o.fitness) {
                return (1);
            } else {
                return (0);
            }
        }
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