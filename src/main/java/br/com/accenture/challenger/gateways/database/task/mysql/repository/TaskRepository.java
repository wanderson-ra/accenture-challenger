package br.com.accenture.challenger.gateways.database.task.mysql.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.accenture.challenger.domains.Task;

@Repository
@Transactional
public interface TaskRepository extends JpaRepository<Task, Long> {

}
