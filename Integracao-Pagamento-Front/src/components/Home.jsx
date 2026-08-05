import { Link } from 'react-router-dom'
import '../css/Home.css'

export default function Home() {
  return (
    <div className="home">
      <div className="bg-grid" aria-hidden="true" />
      <div className="bg-glow" aria-hidden="true" />

      <header className="home__topbar">
        <Link to="/register" className="home__cta-link">Criar conta</Link>
      </header>

      <main className="home__main">
        <section className="hero">
          <div className="badge">
            <span className="badge__dot" />
            pague com Pix via Mercado Pago
          </div>

          <h1 className="hero__title">
            Pague com Pix direto pela API.
          </h1>

          <p className="hero__description">
            Esta é uma integração com a API do Mercado Pago que permite
            pagar com Pix direto pelo app — sem checkout hospedado,
            sem redirecionamento, sem precisar abrir outra tela.
          </p>

          <div className="hero__actions">
            <Link to="/register" className="btn btn--primary">
              Criar conta
              <svg viewBox="0 0 24 24" aria-hidden="true" className="btn__icon">
                <path d="M4 12h15M13 6l6 6-6 6" stroke="currentColor" strokeWidth="2" fill="none" />
              </svg>
            </Link>
            <a href="#como-funciona" className="btn btn--ghost">Como funciona</a>
          </div>
        </section>

        <section className="features" id="como-funciona">
          <article className="feature">
            <div className="feature__index">01</div>
            <h3 className="feature__title">Crie a sua conta</h3>
            <p className="feature__text">
              Cadastro com CPF, e-mail e senha. Sem aprovação manual,
              sem envio de documentos.
            </p>
          </article>

          <article className="feature">
            <div className="feature__index">02</div>
            <h3 className="feature__title">Pague com Pix</h3>
            <p className="feature__text">
              Informe valor e descrição, e a API devolve um QR Code
              pronto pra você pagar no banco.
            </p>
          </article>

          <article className="feature">
            <div className="feature__index">03</div>
            <h3 className="feature__title">Acompanhe o pagamento</h3>
            <p className="feature__text">
              Cada mudança de status chega via webhook. Você sabe na
              hora quando o Pix foi confirmado ou expirou.
            </p>
          </article>
        </section>

        <section className="api">
          <div className="api__head">
            <span className="api__tag">API</span>
            <h2 className="api__title">Construído sobre a API do Mercado Pago</h2>
            <p className="api__sub">
              Autenticação por access token, endpoints REST e webhooks
              para tudo que muda no pagamento.
            </p>
          </div>

          <div className="api__endpoints">
            <div className="endpoint">
              <span className="endpoint__method endpoint__method--post">POST</span>
              <span className="endpoint__path">/v1/payments</span>
              <span className="endpoint__desc">gera um pagamento Pix</span>
            </div>
            <div className="endpoint">
              <span className="endpoint__method endpoint__method--get">GET</span>
              <span className="endpoint__path">/v1/payments/{'{id}'}</span>
              <span className="endpoint__desc">consulta o status do pagamento</span>
            </div>
            <div className="endpoint">
              <span className="endpoint__method endpoint__method--post endpoint__method--hook">POST</span>
              <span className="endpoint__path">/webhooks/mercadopago</span>
              <span className="endpoint__desc">recebe notificações de status</span>
            </div>
          </div>
        </section>
      </main>

      <footer className="home__foot">
        <span><a href='https://github.com/phelipeGit'>Feito por PhelipeGit</a></span>
      </footer>
    </div>
  )
}