package com.syf.chat;


/**
 * 服务端启动类
 */
public class ServerStartup {
    public static void main(String[] args) {
        start(createBrokerController(args));
    }

    public static void start(ServerController controller) {
        try {
            //当JVM准备关闭时，NamesrvController的shutdown方法将被调用，安全地关闭 NameServer 的所有相关组件和资源
            Runtime.getRuntime().addShutdownHook(new Thread(controller::shutdown));
            controller.start();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    private static ServerController createBrokerController(String[] args) {
        //创建 BrokerController 的实例
        ServerController controller = new ServerController();
        boolean initialize = controller.initialize();
        if (!initialize) {
            //初始化失败则关闭并退出
            controller.shutdown();
            System.exit(-3);
        }
        return controller;
    }
}
