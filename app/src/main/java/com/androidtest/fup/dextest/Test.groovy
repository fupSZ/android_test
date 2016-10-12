
public class Test {
    public static void main(def vag) {
        def str = "hello world"
        println str
        println str.class//查看变量类型

        def str1 = ["hello1", "hello2", "hello3"]
        println str1.class
        printVar(str1)
    }

    def printVar(ArrayList<String> strings) {
        for(String str : strings) {
            println str + "\n"
        }
    }
}