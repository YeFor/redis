package com.ye.redis.net;

import com.sun.istack.internal.NotNull;

class CommandCheck {
    private final static String[] commands = {"dset","lset", "sset", "get", "del", "expire"};

    /**
     * 检查格式如:set key value形式的命令串是否合法
     * @param command 命令字符串
     * @return 返回值
     */
    static boolean checkCommand(@NotNull String command){

        String[] commands =  command.split(" ");
        ////命令长度小于等于1不正确
        if(commands.length <= 1){
            return false;
        }
        ///根据命令类型判断命令是否正确
        switch(commands[0].toLowerCase()){
            case "dset":
            case "expire":
                return commands.length == 3 && isCommand(commands[0]);
            case "lset":
            case "sset":
                return commands.length >= 3 && isCommand(commands[0]);
            case "get":
            case "del":
                return commands.length == 2 && isCommand(commands[0]);
            default:
                return  false;
        }
    }

    /**
     * 判断给定字符串是否是命令
     * @param command 命令字符串
     * @return 该字符串为命令返回true,否则返回false
     */
    private static boolean isCommand(String command){
        for(String key : commands){
            if(command.equalsIgnoreCase(key)){
                return true;
            }//end if
        }//end for
        return false;
    }
}
