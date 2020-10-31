package api;

public interface IAuthentication <T,C>{
     T login(C c1, C c2);
     T logout();
}
