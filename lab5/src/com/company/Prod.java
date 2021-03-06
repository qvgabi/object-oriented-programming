package com.company;

import java.util.ArrayList;
import java.util.List;

public class Prod extends Node {
    List<Node> args = new ArrayList<>();

    Prod(){}

    Prod(Node n1){
        args.add(n1);
    }
    Prod(double c){
        args.add(new Constant(c));

    }

    Prod(Node n1, Node n2){
        args.add(n1);
        args.add(n2);
    }
    Prod(double c, Node n){
        args.add(new Constant(c));
        args.add(n);
    }



    Prod mul(Node n){
        args.add(n);
        return this;
    }

    Prod mul(double c){
        args.add(new Constant(c));
        return this;
    }


    @Override
    double evaluate() {
        double result =1;
        for(Node node : args){
            result*=node.evaluate();
        }
        return sign*result;
    }
    int getArgumentsCount(){return args.size();}

    @Override
    Node diff(Variable variable) {
        Sum s = new Sum();
        for(int i=0;i<args.size();i++){
            Prod p= new Prod();
            for(int j=0;j<args.size();j++){
                Node f = args.get(j);
                if(j==i)p.mul(f.diff(variable));
                else p.mul(f);
            }
            s.add(p);
        }
        return s;
    }

    @Override
    boolean isZero() {
        return false;
    }


    public String toString(){
        StringBuilder b =  new StringBuilder();
        if(sign<0)b.append("-");
        int counter=0;
        for(Node node:args){
            if(counter!=0){
                b.append("*");
            }
            if(node.sign<0)b.append("(");

            b.append(node.toString());
            if(node.sign<0)b.append(")");
            counter++;

        }

        return b.toString();
    }



}