//package com.sndj.recipe.proxy.dynamicproxy;
//
//public final class $Proxy0 extends Proxy
//    implements IProxyClass
//{
//
//    public $Proxy0(InvocationHandler invocationhandler)
//    {
//        super(invocationhandler);
//    }
//
//    public final boolean equals(Object obj)
//    {
//        try
//        {
//            return ((Boolean)super.h.invoke(this, m1, new Object[] {
//                obj
//            })).booleanValue();
//        }
//        catch(Error _ex) { }
//        catch(Throwable throwable)
//        {
//            throw new UndeclaredThrowableException(throwable);
//        }
//    }
//
//    public final void doSomething(int i)
//    {
//        try
//        {
//            super.h.invoke(this, m3, new Object[] {
//                Integer.valueOf(i)
//            });
//            return;
//        }
//        catch(Error _ex) { }
//        catch(Throwable throwable)
//        {
//            throw new UndeclaredThrowableException(throwable);
//        }
//    }
//
//    public final int hashCode()
//    {
//        try
//        {
//            return ((Integer)super.h.invoke(this, m0, null)).intValue();
//        }
//        catch(Error _ex) { }
//        catch(Throwable throwable)
//        {
//            throw new UndeclaredThrowableException(throwable);
//        }
//    }
//
//    public final String toString()
//    {
//        try
//        {
//            return (String)super.h.invoke(this, m2, null);
//        }
//        catch(Error _ex) { }
//        catch(Throwable throwable)
//        {
//            throw new UndeclaredThrowableException(throwable);
//        }
//    }
//
//    private static Method m1;
//    private static Method m3;
//    private static Method m0;
//    private static Method m2;
//
//    static
//    {
//        try
//        {
//            m1 = Class.forName("java.lang.Object").getMethod("equals", new Class[] {
//                Class.forName("java.lang.Object")
//            });
//            m3 = Class.forName("io.github.brightloong.proxy.IProxyClass").getMethod("doSomething", new Class[] {
//                Integer.TYPE
//            });
//            m0 = Class.forName("java.lang.Object").getMethod("hashCode", new Class[0]);
//            m2 = Class.forName("java.lang.Object").getMethod("toString", new Class[0]);
//        }
//        catch(NoSuchMethodException nosuchmethodexception)
//        {
//            throw new NoSuchMethodError(nosuchmethodexception.getMessage());
//        }
//        catch(ClassNotFoundException classnotfoundexception)
//        {
//            throw new NoClassDefFoundError(classnotfoundexception.getMessage());
//        }
//    }
//}