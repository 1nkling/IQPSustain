package com.example.SustainibilityStoplight;

import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.example.SustainibilityStoplight.Struct.QuestionAndResponse;
import com.example.SustainibilityStoplight.Struct.Response;

import java.util.ArrayList;
import java.util.Stack;


/**
 * Created by peterdebrine on 2/13/17.
 */

public class BooleanFamily extends iFamily implements View.OnClickListener {

    // Location in the tree
    TreeNode currentNode;

    // Root of the tree, seemed neat
    TreeNode root;

    // A stack of nodes
    Stack<TreeNode> nodes;


    BooleanFamily(ArrayList<QuestionAndResponse> qrs){
        super(qrs);
    }

    @Override
    void init() {
        nodes = new Stack<TreeNode>();
        ArrayList<QuestionAndResponse> ends = new ArrayList<>();
        ArrayList<QuestionAndResponse> parents = new ArrayList<>();
        for (QuestionAndResponse qr : qrs){
            int famType = qr.getQuestion().getMyFamType();
            if (famType == 0){
                root = new TreeNode(qr);
            } else if (famType < 0){
                ends.add(qr);
            } else if(famType > 0){
                parents.add(qr);
            }
        }
        for (int j = 0; j < ends.size(); j++) {

            boolean swapped = true;
            for (int i = 0; i < ends.size() - 1; i++) {
                if (ends.get(i).getQuestion().getMyFamType() > ends.get(i + 1).getQuestion().getMyFamType()) {
                    swap(i, ends);
                    swapped = false;
                }
            }
            if (swapped) {
                break;
            }
        }
        for (int j = 0; j < parents.size(); j++) {

            boolean swapped = true;
            for (int i = 0; i < parents.size() - 1; i++) {
                if (ends.get(i).getQuestion().getMyFamType() < ends.get(i + 1).getQuestion().getMyFamType()) {
                    swap(i, ends);
                    swapped = false;
                }
            }
            if (swapped) {
                break;
            }
        }

        ArrayList<TreeNode> currentLevel = new ArrayList<>();

        for (QuestionAndResponse qr : ends){
            TreeNode node = new TreeNode(qr);
            currentLevel.add(node);
        }
        while (parents.size() != 0) {
            ArrayList<TreeNode> newLevel = new ArrayList<>();
            while(currentLevel.size() != 0){
                TreeNode node = new TreeNode(parents.remove(0));
                TreeNode node1 = currentLevel.remove(0);
                TreeNode node2 = currentLevel.remove(0);
                node.right = node1;
                node.left = node2;
                newLevel.add(node);
            }
            currentLevel = newLevel;

        }

        this.root.right = currentLevel.remove(0);
        this.root.left = currentLevel.remove(0);
        currentNode = new TreeNode(root);
        this.addView();
        while(currentNode.root.getResp().getResp() != -1){
            nodes.push(currentNode);
            next();
        }
    }

    private void next() {
        int temp = currentNode.root.getResp().getResp();
        if (currentNode.nodeID >= 0) {
            if (temp == 1) {
                currentNode = new TreeNode(currentNode.right);
            } else if (temp == 0) {
                currentNode = new TreeNode(currentNode.left);
            }
            if (nodes.peek().nodeID >= 0 & currentNode != null) {
                addView();
            }
        }
    }

    @Override
    ArrayList<Response> getResponses() {
        ArrayList<Response> r = new ArrayList<>();
        for (TreeNode n : nodes){
            r.add(n.root.getResp());
        }
        return r;
    }

    @Override
    int getScore() {
        int score = 0;
        for (TreeNode n : nodes){
            score += n.root.getScore();
        }
        return score;
    }

    @Override
    void update(iFamily fam) {

    }

    private void addView(){
        LinearLayout qr = currentNode.root.getContent();
        BooleanQuestion iq  = (BooleanQuestion) qr.getChildAt(1);
        iq.getChildAt(0).setOnClickListener(this);
        iq.getChildAt(1).setOnClickListener(this);
        this.addView(qr);
        Log.d("Adding view: ", qr.toString());
    }

    @Override
    public void onClick(View view) {
        nodes.push(currentNode);
        BooleanQuestion clicked = (BooleanQuestion) view.getParent();
        while (clicked.getQuestion().getMyFamType() != nodes.peek().nodeID) {
            Log.d("Trying to remove: ", clicked.toString());
            nodes.peek().root.getIq().setAnswer(-1);
            removeView(nodes.pop().root);
        }
        currentNode = nodes.peek();
        next();
    }

    private void swap(int i, ArrayList<QuestionAndResponse> src){
        QuestionAndResponse temp = src.get(i);
        src.add(i, src.get(i+1));
        src.add(i+1, temp);
    }

    @Override
    public void setResponses(ArrayList<Response> r){
        currentNode = root;
        for (Response resp : r){
            currentNode.root.setResp(resp);
            next();
        }
    }

    private class TreeNode {

        QuestionAndResponse root;
        TreeNode left; // no
        TreeNode right; // yes
        int nodeID;

        TreeNode(QuestionAndResponse qr){
            this.root = qr;
            this.nodeID = qr.getQuestion().getMyFamType();
        }

        TreeNode(TreeNode node) {
            this.root = node.root;
            this.left = node.left;
            this.right = node.right;
            this.nodeID = root.getQuestion().getMyFamType();
        }
    }

}
