package RSA;

public class Test {
    public static void main(String[] args){
        int text = 1251;
        int key = 632;
        int n = 1870;
        int m = func(text,key,n);
        System.out.println(m);
    }
    private static int func(int text, int key, int n) {
        long result;
        if(key%2==0){
            int num = key/2;
            System.out.println("num:"+num);
            result = 1;
            for(int i=0;i<num;i++){
                result = (long) (result * (Math.pow(text,2)%n))%n;
            }
            result = result % n;
        }
        else {
            int num = (key-1)/2;
            System.out.println("num:"+num);
            result = 1;
            for(int i=0;i<num;i++){
                result = (long) (result * (Math.pow(text,2)%n))%n;
                System.out.println(i+" "+result);
            }
            result = result * (text%n)%n;
            result = result % n;
        }
        return (int) result;
    }
}
