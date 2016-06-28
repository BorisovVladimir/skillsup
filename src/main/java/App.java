import annotation.CustomDateFormat;
import annotation.JsonValue;
import people.Human;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Created by set on 28.06.2016.
 */
public class App {

    public static String toJson(Object o) throws IllegalAccessException {
        Class c = o.getClass();
        Field[] fields = c.getDeclaredFields();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{\n");

        for (int i = 0; i < fields.length; i++)
        {
            Field f = fields[i];
            f.setAccessible(true);

            Annotation[] annotations = f.getDeclaredAnnotations();
            if (annotations.length == 0)
            {
                stringBuilder.append("\"").append(f.getName()).append("\":");
                stringBuilder.append("\"").append(f.get(o)).append("\"");
            }
            for (Annotation a: annotations)
            {
                if (a instanceof JsonValue)
                {
                   String name = ((JsonValue) a).name();
                   stringBuilder.append("\"").append(name).append("\":");
                }
                else
                {
                    stringBuilder.append("\"").append(f.getName()).append("\":");
                }

                if (a instanceof CustomDateFormat)
                {
                    LocalDate localDate = (LocalDate) f.get(o);
                    stringBuilder.append("\"").append(localDate.format(DateTimeFormatter.ofPattern(((CustomDateFormat) a).format()))).append("\"");
                }
                else
                {
                    stringBuilder.append("\"").append(f.get(o)).append("\"");
                }
            }
            if (i+1 == fields.length)
            {
                stringBuilder.append("\n}");
            }
            else
            {
                stringBuilder.append(",\n");
            }
        }

        return  stringBuilder.toString();
    }

    public static void main(String[] args) throws IllegalAccessException {
        Human human = new Human("Derek", "McFly", "SingSong", LocalDate.of(1985,1,5));
        System.out.println(App.toJson(human));
    }
}
