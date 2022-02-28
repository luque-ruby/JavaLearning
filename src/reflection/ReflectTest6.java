package reflection;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/** 反射获取泛型*/
public class ReflectTest6 {
    public void test1(Map<String, User> map, List<User> users) {
        System.out.println("test1");
    }

    public Map<String, User> test2() {
        System.out.println("test2");
        return null;
    }

    public static void main(String[] args) throws Exception {
        Method method =ReflectTest6.class.getMethod("test1", Map.class, List.class);
        Type[] genericParameterTypes = method.getGenericParameterTypes();
        for (Type genericParameterType : genericParameterTypes) {
            System.out.println("genericParameterType" + genericParameterType);
            if (genericParameterType instanceof ParameterizedType) {
                Type[] actualTypeArguments = ((ParameterizedType) genericParameterType).getActualTypeArguments();
                for (Type actualTypeArgument : actualTypeArguments) {
                    System.out.println("actualTypeArgument" + actualTypeArgument);
                }
            }
        }

        System.out.println("--------------------------------");

        Method method2 =ReflectTest6.class.getMethod("test2", null);
        Type genericReturnType = method2.getGenericReturnType();
        if (genericReturnType instanceof ParameterizedType) {
            Type[] actualTypeArguments = ((ParameterizedType)genericReturnType).getActualTypeArguments();
            for (Type actualTypeArgument : actualTypeArguments) {
                System.out.println(actualTypeArgument);
            }
        }
    }
}