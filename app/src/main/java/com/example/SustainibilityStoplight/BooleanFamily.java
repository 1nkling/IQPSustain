package com.example.SustainibilityStoplight;

import android.view.View;
import android.widget.LinearLayout;

import com.example.SustainibilityStoplight.Struct.QuestionAndResponse;
import com.example.SustainibilityStoplight.Struct.Response;

import java.util.ArrayList;


/**
 * Created by peterdebrine on 2/13/17.
 */

public class BooleanFamily extends iFamily implements View.OnClickListener {

    // Location in the tree
    TreeNode currentNode;

    // Root of the tree, seemed neat
    TreeNode root = new TreeNode(0);

    // Functionally a stack of nodes
    ArrayList<TreeNode> nodes = new ArrayList<>();


    BooleanFamily(ArrayList<QuestionAndResponse> qrs){
        super(qrs);
    }

    @Override
    void init() {
        ArrayList<QuestionAndResponse> ends = new ArrayList<>();
        ArrayList<QuestionAndResponse> parents = new ArrayList<>();
        for (QuestionAndResponse qr : qrs){
            int famType = qr.getQuestion().getMyFamType();
            if (famType == 0){
                root.root = qr;
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
            TreeNode node = new TreeNode(qr.getQuestion().getMyFamType());
            node.root = qr;
            currentLevel.add(node);
        }
        while (parents.size() != 0) {
            ArrayList<TreeNode> newLevel = new ArrayList<>();
            while(currentLevel.size() != 0){
                TreeNode node = new TreeNode(parents.get(0).getQuestion().getMyFamType());
                node.root = parents.remove(0);
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
        currentNode = root;
        this.addView();
    }

    private void next() {
        nodes.add(currentNode);
        int temp = currentNode.root.getIq().getAnswer();
        if (temp == 1){
            currentNode = currentNode.right;
        } else if (temp == 0){
            currentNode = currentNode.left;
        } if (currentNode.nodeID >= 0){
            addView();
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
        return  score;
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
    }

    @Override
    public void onClick(View view) {
        int clicked = ((BooleanQuestion) view).getQuestion().getMyFamType();
        if (clicked < currentNode.nodeID & clicked < 0) {
            while (clicked < nodes.get(0).nodeID) {
                nodes.remove(0);
            }
            currentNode = nodes.get(0);
        }
        next();
    }

    private void swap(int i, ArrayList<QuestionAndResponse> src){
        QuestionAndResponse temp = src.get(i);
        src.add(i, src.get(i+1));
        src.add(i+1, temp);
    }

    private class TreeNode {

        QuestionAndResponse root;
        TreeNode left;
        TreeNode right;
        int nodeID;

        TreeNode(int id){
            this.nodeID = id;
        }
    }

}
