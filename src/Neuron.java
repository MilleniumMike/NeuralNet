
public class Neuron {

    public double w[];
    int border, t;

    ///////////////////////////////////////////////////
    public Neuron(double w[], int border, int t) {
        this.w = w;
        this.border = border;
        this.t = t;
    }

    public Neuron(double w[], int border) {
        this.w = w;
        this.border = border;
        this.t = 0;
    }

    public Neuron(double w[]) {
        this.w = w;
        this.border = 0;
        this.t = 0;
    }

    public void add_w(double summ, int i) {
        this.w[i] += summ;
    }

    public int sum(int x[]) {
        int net = 0;
        for (int i = 0; i < w.length; i++) {
            net += x[i] * w[i];
        }
        net -= t;
        return net;
    }

    public int out(int x[]) {
        int net = 0;
        for (int i = 0; i < w.length; i++) {
            net += x[i] * w[i];
        }
        net -= t;
        if (net >= border) {
            return 1;
        } else return -1;
    }
}
