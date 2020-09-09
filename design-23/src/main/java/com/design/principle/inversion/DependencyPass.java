package com.design.principle.inversion;

public class DependencyPass {
    public static void main(String[] args) {
        ChangHong changHong = new ChangHong();

        //1.通过接口进行依赖传递
//        OpenAndClose openAndClose = new OpenAndClose();
//        openAndClose.open(changHong);

        //2.通过构造器进行依赖传递
//        OpenAndClose openAndClose = new OpenAndClose(changHong);
//        openAndClose.open();

        //3.通过setter方法进行依赖传递
        OpenAndClose openAndClose = new OpenAndClose();
        openAndClose.setTv(changHong);
        openAndClose.open();
    }
}

//-----------------------------------------------------------------------------------
//1.方式1：通过接口传递实现依赖
//开关的接口
//interface IOpenAndClose {
//    public void open(ITV tv); //抽象方法，接收接口
//}
//
//interface ITV {
//    public void play();
//}
//
//class OpenAndClose implements IOpenAndClose {
//
//    @Override
//    public void open(ITV tv) {
//        tv.play();
//    }
//}
//-----------------------------------------------------------------------------------

//方式2：通过构造方法依赖传递
//interface IOpenAndClose {
//    public void open(); //抽象方法，接收接口
//}
//
//interface ITV {
//    public void play();
//}
//
//class OpenAndClose implements IOpenAndClose {
//    public ITV tv;
//    public OpenAndClose(ITV tv){
//        this.tv = tv;
//    }
//    @Override
//    public void open() {
//        this.tv.play();
//    }
//}
//-----------------------------------------------------------------------------------

//方式3；通过setter方法传递
interface IOpenAndClose {
    public void open(); //抽象方法
    public void setTv(ITV tv);
}

interface ITV {
    public void play();
}

class OpenAndClose implements IOpenAndClose {
    private ITV tv;

    public void setTv(ITV tv) {
        this.tv = tv;
    }

    @Override
    public void open() {
        this.tv.play();
    }
}
//-----------------------------------------------------------------------------------
class ChangHong implements ITV{
    @Override
    public void play() {
        System.out.println("ch开机");
    }
}
