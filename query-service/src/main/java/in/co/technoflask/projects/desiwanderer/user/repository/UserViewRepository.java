package in.co.technoflask.projects.desiwanderer.user.repository;

import in.co.technoflask.projects.desiwanderer.user.entity.UserView;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserViewRepository
    extends JpaRepository<UserView, UUID>, JpaSpecificationExecutor<UserView> {}
