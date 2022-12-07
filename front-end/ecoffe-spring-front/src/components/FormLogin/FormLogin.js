export default function FormLogin () {
    return (
        <div className="form-container sign-in-container">
            <form action="/iniciarsessao/login" method="POST" id="formularioLogin">
                <h1>Login</h1>
                <div className="form-Login">
                    <input type="email" name="email" placeholder="E-mail" id="loginemail" />
                </div>
                <div className="form-Login">
                    <input type="password" name="password" placeholder="Senha" id="loginsenha" />
                </div>
                <a href="#">Esqueceu sua senha?</a>
                <button type="submit">Login</button>
            </form>
        </div>
    );
}