package com.myclinic.part2project.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import com.myclinic.part2project.model.Question;
import com.myclinic.part2project.model.Symptom;

public class Graph implements Serializable{

	public Map<String, List<Question>> adj;
	
	private String question;
	private String answer="";

	public Graph() {
		this.adj = new HashMap<>();
	}

	public List<Question> getAdj(String key) {
		return adj.get(key);
	}

	public void addEdge(List<Symptom> symptom) {
		for (Symptom e : symptom) {
			adj.get(e.getSrc()).add(new Question(e.getDest(), e.getSymptomName()));
		}
	}

	public void addQuestion(List<Question> nodes) {
		for (Question q : nodes) {
			if (!adj.containsKey(q.getValue()))
				adj.put(q.getValue(), new ArrayList<Question>());
		}
		return;
	}

	 

	public Set<String> breadthFirstTraversal(Graph graph, String root) {
		Set<String> visited = new LinkedHashSet<String>();
		Queue<String> queue = new LinkedList<String>();
		queue.add(root);
		visited.add(root);
		int si = 0;
		int le = 0;

		while (!queue.isEmpty()) {

			si = queue.size();
			while (si > 0) {
				si--;
				String vertex = queue.poll();
				this.setQuestion(vertex);
				System.out.println(vertex);
				 
				for (Question v : graph.getAdj(vertex)) {

					if (!visited.contains(v.getValue())) {

						//if (v.getWeight().equals(answer)) {
							visited.add(v.getValue());
							queue.add(v.getValue());
						//}
						/*if (answer.equals("no")) {
							visited.add(v.getValue());

							queue.add(v.getValue());
						}*/
					}
				}
			}
			le++;
		}
		return visited;
	}

	public Map<String, List<Question>> getAdj() {
		return adj;
	}

	public void setAdj(Map<String, List<Question>> adj) {
		this.adj = adj;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
}