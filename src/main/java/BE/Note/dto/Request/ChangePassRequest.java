package BE.Note.dto.Request;

public class ChangePassRequest {
    private String changePass;
    private String confirmPass;

    public ChangePassRequest(String changePass, String confirmPass) {
        this.changePass = changePass;
        this.confirmPass = confirmPass;
    }

    public String getChangePass() {
        return changePass;
    }

    public void setChangePass(String changePass) {
        this.changePass = changePass;
    }

    public String getConfirmPass() {
        return confirmPass;
    }

    public void setConfirmPass(String confirmPass) {
        this.confirmPass = confirmPass;
    }

}
