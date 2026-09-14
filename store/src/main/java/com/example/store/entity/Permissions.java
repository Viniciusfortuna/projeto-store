package com.example.store.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "permissoes")
@Getter
@Setter
@Builder
public class Permissions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String nome;
    
    @ManyToOne
    @JoinColumn(name = "user_geracao_id")
    private User userGeracao;
    
    private LocalDateTime createdAt;
    
    @ManyToOne
    @JoinColumn(name = "user_atualizacao_id")
    private User userAtualizacao;
    
    private LocalDateTime updatedAt;
    
    /*Trigger e funções banco
     * 
     * SELECT * FROM permissoes;

		DROP TRIGGER before_updated_permissoes ON permissoes;
		
		
		CREATE TRIGGER before_updated_permissoes
		BEFORE UPDATE ON permissoes
		FOR EACH ROW
		EXECUTE FUNCTION preencher_data_atualizacao();
		
		
		
		CREATE OR REPLACE FUNCTION preencher_data_atualizacao()
		RETURNS TRIGGER
		AS $$
		BEGIN
			NEW.updated_at := CURRENT_TIMESTAMP;
			RETURN NEW;
		END
		$$ LANGUAGE plpgsql;*/
    
}
