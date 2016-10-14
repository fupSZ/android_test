
public class Test {
    public static void main(def vag) {
/*        def str = "hello world"
        println str
        println str.class//查看变量类型

        //test collection
        def str1 = ["hello1", "hello2", "hello3"]
        println str1.class
        print str1[-1]//支持负索引
        print "\n"
*/
        def thisA = new Test()
        def str2 = ["hello1", "hello"]
        str2 = str2 + "hello2"
        str2 = str2 + "hello3"
//        print str2[2]
//        new Test().printVar(str2)

//        str2.each {str->
//            print str + "\n"
//        }
/*
        //Map
        def map = ['name':'john', 'age':'11', 'sex':'boy']
        print map.age + "\n"
        print map['name'] + "\n"

        //closure
        map.each {
            print it
            print "\n"}
        map.each {
            print it.getKey() + "-->" + it.getValue()
            print "\n"
        }
        map.each {key, value->
            print "$key:$value"
            print "\n"
        }
*/

        //var
//        print thisA.sum(1)
//        print thisA.sum(1,2,3)
//        String a = "test"
//        print "haha:" + a
//        new Test().haha(a)
//        sum(1)

        //groovy黑科技，元类
//        def strTest = "hello"
//        print strTest.metaClass
//        print "\n"
//        String.metaClass.up = { delegate.toUpperCase() }
//        print strTest.up()

        //查看元类提供的方法和属性
        String.metaClass.methods.each {
            print it.name + "\n"
        }
        String.metaClass.properties.each {
            print it.name + "\n"
        }
    }

    int sum(... var) {
        def total = 0
        for(i in var) {
            total += i
        }
        return total
    }

//    void haha( a) {
//        print "haha:" + a
//    }
    def haha={ a->
        print a.class
        print "haha:" + a
    }

    def printVar(ArrayList<String> strings) {
        for(String str : strings) {
            println str + "\n"
        }
    }

//    def printVar={strings->
//        strings.each {
//            print it
//        }
//    }
}