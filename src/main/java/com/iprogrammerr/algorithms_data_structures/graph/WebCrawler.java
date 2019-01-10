package com.iprogrammerr.algorithms_data_structures.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class WebCrawler implements Traversable {

	private static final Pattern PATTERN = Pattern.compile("http://(\\w+\\.)*(\\w+)");
	private final String root;
	private final Queue<String> queue;
	private final Set<String> visited;

	public WebCrawler(String root) {
		this.root = root;
		this.queue = new LinkedList<>();
		this.visited = new HashSet<>();
	}

	@Override
	public void traverse() {
		this.queue.clear();
		this.visited.clear();
		this.queue.add(this.root);
		this.visited.add(this.root);
		while (!this.queue.isEmpty()) {
			Matcher matcher = PATTERN.matcher(html(this.queue.poll()));
			while (matcher.find()) {
				String url = matcher.group();
				if (!this.visited.contains(url)) {
					this.visited.add(url);
					System.out.println(String.format("Found website with url: %s", url));
					this.queue.add(url);
				}
			}
		}
	}

	private String html(String url) {
		StringBuilder html = new StringBuilder();
		try {
			URL u = new URL(url);
			try (BufferedReader reader = new BufferedReader(new InputStreamReader(u.openStream()))) {
				String line = reader.readLine();
				while (line != null) {
					html.append(line);
					line = reader.readLine();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return html.toString();
	}
}
