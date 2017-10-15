package com.example.demo.util;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class TTLConcurrentHashMap<K, V> extends ConcurrentHashMap<K, V> {

	private static final long serialVersionUID = -7372506363304310948L;

	private long ttl;
	private ExecutorService executor = Executors.newFixedThreadPool(1);

	private DelayQueue<TTLKey<K>> ttlKeyQueue = new DelayQueue<>();

	private AtomicBoolean stoped = new AtomicBoolean(false);

	public TTLConcurrentHashMap(long ttl) {
		super();
		this.ttl = ttl;

	}

	@Override
	public V put(K key, V value) {
		TTLKey<K> ttlKey = new TTLKey<K>(key, ttl);
		if (ttlKeyQueue.offer(ttlKey)) {
			return super.put(key, value);
		}
		return null;
	}

	@Override
	public V get(Object key) {
		return super.get(key);
	}

	@PostConstruct
	public void init() {
		expireChecker();
	}

	@PreDestroy
	public void destory() {
		stoped.set(true);
		executor.shutdownNow();
	}

	@Override
	public V remove(Object key) {
		Objects.requireNonNull(key, "key Must be noneNull");

		ttlKeyQueue.remove(key);

		return super.remove(key);
	}

	private void expireChecker() {
		System.out.println("expireChecker");
		Runnable task = new Runnable() {

			@Override
			public void run() {
				while (!stoped.get()) {
					try {
						TTLKey<K> ttlKey = ttlKeyQueue.poll(5, TimeUnit.SECONDS);
						if (ttlKey == null) {
							continue;
						}
						K key = ttlKey.getKey();
						if (key != null) {
							remove(key);
						}
					} catch (InterruptedException e) {
						break;
					}
				}

			}
		};
		executor.submit(task);
	}

	static class TTLKey<K> implements Delayed {
		private K key;
		private long effectTime;

		public TTLKey(K key, long time) {
			this.key = key;
			this.effectTime = TimeUnit.NANOSECONDS.convert(time, TimeUnit.MILLISECONDS) + System.nanoTime();
		}

		public K getKey() {
			return key;
		}

		@SuppressWarnings("unchecked")
		@Override
		public int compareTo(Delayed o) {
			TTLKey<K> that = (TTLKey<K>) o;
			if (this.effectTime > that.effectTime) {
				return 1;
			} else if (this.effectTime < that.effectTime) {
				return -1;
			}
			return 0;
		}

		@Override
		public long getDelay(TimeUnit unit) {
			return unit.convert(effectTime - System.nanoTime(), TimeUnit.NANOSECONDS);
		}

	}

}
