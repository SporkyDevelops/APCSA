
public class RoundThings {

    static double calcAreaCircle(double rad){
        return Math.PI * (rad*rad);
    }
    static double calcCircumference(double rad){
        return 2*(Math.PI*rad);
    }
    static double calcSurfaceArea(double rad){
        return 4*(Math.PI*(rad*rad));
    }
    static double calcVolumeSphere(double rad){
        return (4/3)*(Math.PI*(rad*rad*rad));
    }
    
}