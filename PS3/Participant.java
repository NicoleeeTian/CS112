public class Participant {
    private String name;
    private int age;

    // add the new methods here
    public String getName(){
        return this.name;
    }
    public int getAge(){
        return this.age;
    }
    public void setName(String newName){
        if (newName == null){
            throw new IllegalArgumentException();
        }else{
            this.name = newName;
        }
    }

    public void setAge(int newAge){
        if (newAge < 0){
            throw new IllegalArgumentException();
        }else{
            this.age = newAge;
        }
    }

    public Participant(String n, int a){
        this.setName(n);
        this.setAge(a);
    }


    public static void main(String[] args){
        Participant qb = new Participant("Tom Brady", 40);
        qb.setAge(43);
        String mvp = qb.getName();
        System.out.println(mvp);
        System.out.println(qb.getAge());

    }

}
