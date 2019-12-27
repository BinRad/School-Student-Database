package Project2;

import Project1.MyColor;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.ArcType;

import java.util.ArrayList;
import java.util.Map;

import static java.lang.Math.*;

public class PieChart {
    private double n, w, h, x, y, total;
    ArrayList<String> S = new ArrayList<String>();
    ArrayList<Double> P = new ArrayList<Double>();
    public PieChart(int w, int h){
        this.w = w;
        this.h = h;
        n = 0;
    }
    public void setX(double X){
        x = X;
    }
    public void setY(double Y){
        y = Y;
    }
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public void setN(double N){
        if(N > total){
            System.out.println("ERROR: n is too big \n" +
                    "Try setting N to: "+ total);
        }else {
            n = N;
        }
    }
    public double getN(){
        return n;
    }
    public void makeProbability(Map<String, Integer> M){
        double tot = 0;
        for(String key:M.keySet()){
            tot += M.get(key);
        }
        System.out.println(tot);
        for(String key:M.keySet()){
            System.out.println(key);
            addEntry(key, (M.get(key)/tot), false);
        }
        for(int i = 0; i < total; i++){
            System.out.println("s: " + S.get(i) + "     p: " + P.get(i));
        }
    }
    public void addEntry(String s, double p, boolean sort){
        if(sort) {
            System.out.println("n is " + n + "  and the size of P is " + P.size() + "    s is:" + s + "    p is: " + p);
            for (int i = (int) (total - 1); i >= 0; i -= 1) {
                //System.out.println("Name" + S.get(i) + "    p: " + p + "     P.get(i): " + P.get(i) + "   i: "+ i);
                if (!P.isEmpty()) {
                    if (p < P.get(0)) {
                        P.add(0, p);
                        S.add(0, s);
                        n += 1;
                        total++;
                        return;
                    }
                    if (p > P.get(i)) {
                        P.add(i + 1, p);
                        S.add(i + 1, s);
                        n += 1;
                        total++;
                        return;
                    }
                }
            }
            P.add(p);
            S.add(s);
            n += 1;
            total++;
        }
        else{
                P.add(p);
                S.add(s);
                n += 1;
                total++;
        }
    }
    public static void setColor(GraphicsContext gc, double i){
        double q = 0;
        for(MyColor COL : MyColor.values()) {
            if (q == i) {
                gc.setFill(COL.paint());
                return;
            }
            q++;
        }
    }
    public String getColor(GraphicsContext gc){
        return gc.getFill().toString();
    }
    public void draw(GraphicsContext gc){
        double currentAngle = 0;
        double colorCount = 0;
        adjustProbability();
        for(int i =(int) (total-1); i >= (total -n ); i--){
            double arcExtent = P.get(i)*360;
            setColor(gc, colorCount);
            colorCount++;
            gc.strokeArc(x-w/2, y-h/2, w, h, currentAngle, arcExtent, ArcType.ROUND);
            gc.fillArc(x-w/2, y-h/2, w, h, currentAngle, arcExtent, ArcType.ROUND);
            double radius = sqrt(w*w + h*h);
            double tx = x + radius*cos(Math.toRadians(currentAngle+arcExtent/2))/2-50;
            double ty = y - radius*sin(Math.toRadians(currentAngle+arcExtent/2))/2;
            gc.fillText(S.get(i) + ", "+ P.get(i),tx,ty) ;
            currentAngle = currentAngle + arcExtent;
        }
    }
    public void addOther(){
        double other = 0;
        //for(int i = 0; i< n; i++){
        for(int i =(int) (total-1); i >= (total-n); i--){
            other += P.get(i);
        }
        other = 1-other;
        P.add(other);
        S.add("All other events");
        n++;
        total++;
    }
    public void removeOther(){
        P.remove(n);
        S.remove(n);
        n--;
        total--;
    }
    public void adjustProbability(){
        double newProbabilityTotal = 0;
        for(int i =(int) (total-1); i >= (total -n ); i--){
            newProbabilityTotal += P.get(i);
        }
        for(int i =(int) (total-1); i >= (total -n ); i--){
            P.set(i, P.get(i)/newProbabilityTotal);
        }
    }
}
