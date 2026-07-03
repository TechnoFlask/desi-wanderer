package in.co.technoflask.projects.desiwanderer.user.repository;

import in.co.technoflask.projects.desiwanderer.user.entity.User;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {}
