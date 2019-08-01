package ru.otus.hw04;

import javassist.ClassPool;
import javassist.ByteArrayClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import org.w3c.dom.ls.LSOutput;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class ClassTransformer implements ClassFileTransformer {
    private ClassPool pool= ClassPool.getDefault();
    public byte[] transform(ClassLoader loader,
                            String className,
                            Class classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) throws IllegalClassFormatException {
        try {
            pool.insertClassPath(new ByteArrayClassPath(className, classfileBuffer));
            CtClass cclass = pool.get(className.replaceAll("/", "."));
            if (!cclass.getName().startsWith("ru.otus.hw04")) {
                return null;
            }

            for (CtMethod currentMethod : cclass.getDeclaredMethods()) {
                Log annotation = (Log) currentMethod.getAnnotation(Log.class);
                if (annotation != null) {
                //раз insertBefore, значит все должно быть в обратном порядке
                    currentMethod.insertBefore("{ System.out.println(\"" +"\"); }");
                    currentMethod.insertBefore("{ System.out.print($1); }");
                    currentMethod.insertBefore("{System.out.print(\"" + ", param: " +"\");}");
                    currentMethod.insertBefore("{System.out.print(\"" + currentMethod.getName() + "\");}");
                    currentMethod.insertBefore("{System.out.print(\"" + "executed method: " +"\");}");
                }
            }

            return cclass.toBytecode();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
