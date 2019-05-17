package com.ws.demo1.TestService;

public class ThreadA extends Thread {

    private LockService lockService;
    public ThreadA(LockService lockService){
        this.lockService = lockService;
    }

    @Override
    public void run() {
        lockService.seckill();
    }
    public static void main(String[] args){
        LockService lockService = new LockService();
        for(int i=0;i<50;i++){
            ThreadA threadA = new ThreadA(lockService);
            threadA.start();
        }
    }
}
