function comparePasswords() {
    let newPassword = $('#newPassword').val();
    let confirmNewPassword = $('#confirmNewPassword').val();

    if (!newPassword || !confirmNewPassword || newPassword !== confirmNewPassword) {
        $('#message').text("Введены некорректные данные");
    } 
}