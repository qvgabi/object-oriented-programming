package com.company;

import java.util.ArrayList;
import java.util.List;

public class Sum extends Node {

    List<Node> args = new ArrayList<>();

    Sum(){}

    Sum(Node n1, Node n2){
        args.add(n1);
        args.add(n2);
    }


    Sum add(Node n){
        args.add(n);
        return this;
    }

    Sum add(double c){
        args.add(new Constant(c));
        return this;
    }

    Sum add(double c, Node n) {
        Node mul = new Prod(c,n);
        args.add(mul);
        return this;
    }

    @Override
    double evaluate() {
        double result =0;
        for(Node node : args){
            result+=node.evaluate();
        }
        return sign*result;
    }
    Node diffVanilla(Variable var) {
        Sum r = new Sum();
        for(Node n:args){
            r.add(n.diff(var));
        }
        return r;
    }

    int getArgumentsCount(){return args.size();}

    public String toString(){
        StringBuilder b =  new StringBuilder();
        if(sign<0)b.append("-(");
        int counter=0;
        for(Node node:args){
            if(node.sign>0 && counter!=0){
                b.append("+");
            }
            b.append(node.toString());
            counter++;
        }

        if(sign<0)b.append(")");
        return b.toString();
    }


}