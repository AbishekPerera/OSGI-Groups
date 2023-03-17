package authmanagepublisher;

public interface AuthManageServiceInterface {

	void addUser();
    boolean authUser();
    void getAllUsers();
    void getUserByID();
    void deleteUserByID();
    void updateUserByID();
	
}
