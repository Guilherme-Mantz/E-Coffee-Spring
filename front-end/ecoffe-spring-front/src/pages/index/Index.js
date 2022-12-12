import Header from '../../components/Header/Header';
import Carrossel from '../../components/Carrossel/Carrossel';
import Footer from '../../components/Footer/Footer';

export default function Index () {
    return(
        <>
        <Header />
            <main>
                <div className="container">
                    <Carrossel/>
                </div>
            </main>
        <Footer />
        </>
    );
};