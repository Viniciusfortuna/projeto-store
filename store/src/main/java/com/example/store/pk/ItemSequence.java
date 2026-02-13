package com.example.store.pk;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class ItemSequence implements Serializable {
	private Long order_id;
	private Long order_sequence;
}
