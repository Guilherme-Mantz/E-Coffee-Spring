import { Link } from 'react-router-dom';

import './linkUser.css';

export default function LinkUser (props) {

    const { icon, titulo, descricao, link } = props;

    return (
        <div className="card mb-3 col ms-4" id="card-user-options">
            <div className="row g-0">
                <div className="col-md-3 text-center align-self-center">
                    <i className={icon} id="icon-option"></i>
                </div>
                <div className="col-md-9">
                    <div className="card-body">
                        <h4 className="card-title fw-bold">{titulo}</h4>
                        <Link className="card-text" to={link}>{descricao}</Link>
                    </div>
                </div>
            </div>
        </div>
    );
};