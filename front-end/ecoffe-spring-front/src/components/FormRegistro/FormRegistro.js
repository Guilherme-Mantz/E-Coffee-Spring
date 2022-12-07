export default function FormRegistro () {

    return (
        <div className="form-container sign-up-container">
            <form action="" method="POST" id="formCadastro">
                <h1>Cadastre-se</h1>
                <div className="formCadastro">
                    <input type="text" name="nome" placeholder="Nome" id="nome" />
                </div>

                <div className="formCadastro">
                    <input type="text" name="sobrenome" placeholder="Sobrenome" id="sobrenome" />
                </div>

                <div className="formCadastro">
                    <input type="text" name="telefone" maxLength="11" placeholder="Telefone" id="telefone" />
                </div>

                <div className="formCadastro">
                    <input type="text" name="cpf" maxLength="30" placeholder="CPF" id="cpf" />
                </div>

                <div className="formCadastro">
                    <input type="email" name="email" placeholder="E-mail" id="email" />
                </div>

                <div className="formCadastro">
                    <input type="password" name="senha" placeholder="Senha" id="senha" />
                </div>

                <div className="formCadastro">
                    <input type="password" name="confirmarSenha" placeholder="Confirme a Senha" id="confirmarSenha" />
                </div>
                <button style={{marginTop: "17px"}} type="submit">Registrar-se</button>
            </form>
        </div>
    );
};