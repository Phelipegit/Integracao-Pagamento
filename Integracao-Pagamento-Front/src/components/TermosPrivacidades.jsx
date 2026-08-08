import '../css/TermosPrivacidade.css'
import LegalUpdated from './LegalUpdated'


export default function TermosPrivacidade() {
  return (
    <div className="page">
      <div className="bg-grid" aria-hidden="true" />
      <div className="bg-glow" aria-hidden="true" />

      <main className="legal-shell">
        <nav className="legal-nav">
          <a href="#termos">Termos de Uso</a>
          <a href="#privacidade">Política de Privacidade</a>
        </nav>

        {/* ---------------- TERMOS DE USO ---------------- */}
        <section id="termos" className="legal-card">
          <span className="legal-badge">
            <span className="legal-badge__dot" /> Documento legal
          </span>
          <h1 className="legal-card__title">Termos de Uso</h1>
          <LegalUpdated slug="termos" />

          <h2>1. Aceitação dos Termos</h2>
          <p>
            Ao criar uma conta ou utilizar os serviços disponibilizados em
            phelipedev.com.br ("Plataforma"), você declara que leu,
            compreendeu e concorda em se vincular a estes Termos de Uso e à
            nossa Política de Privacidade. Caso não concorde com qualquer
            disposição, não utilize a Plataforma.
          </p>

          <h2>2. Descrição do Serviço</h2>
          <p>
            A Plataforma oferece [descrever brevemente o serviço/produto].
            Nos reservamos o direito de modificar, suspender ou descontinuar,
            total ou parcialmente, o serviço a qualquer momento, mediante
            aviso prévio quando exigido por lei.
          </p>

          <h2>3. Cadastro e Conta</h2>
          <p>
            Para utilizar determinadas funcionalidades, é necessário criar
            uma conta informando dados como nome, CPF, e-mail e senha. Você
            se compromete a:
          </p>
          <ul>
            <li>Fornecer informações verdadeiras, exatas e atualizadas;</li>
            <li>Manter a confidencialidade da sua senha e demais credenciais de acesso;</li>
            <li>Notificar imediatamente qualquer uso não autorizado da sua conta;</li>
            <li>Ser o único responsável por todas as atividades realizadas com seu login.</li>
          </ul>
          <p>
            Reservamo-nos o direito de suspender ou encerrar contas que
            contenham informações falsas, incompletas ou que violem estes
            Termos.
          </p>

          <h2>4. Responsabilidades do Usuário</h2>
          <p>Ao utilizar a Plataforma, você concorda em não:</p>
          <ul>
            <li>Utilizar o serviço para fins ilícitos, fraudulentos ou não autorizados;</li>
            <li>Tentar acessar áreas restritas, dados de outros usuários ou os sistemas internos sem autorização;</li>
            <li>Interferir no funcionamento normal da Plataforma (ataques, engenharia reversa, automações não autorizadas etc.);</li>
            <li>Publicar ou transmitir conteúdo ofensivo, ilegal ou que viole direitos de terceiros.</li>
          </ul>

          <h2>5. Propriedade Intelectual</h2>
          <p>
            Todo o conteúdo da Plataforma — incluindo marca, layout, código-
            fonte, textos, gráficos e logotipos — é de propriedade de
            [Nome da Empresa] ou de seus licenciantes, sendo protegido pela
            legislação de propriedade intelectual aplicável. É vedada a
            reprodução, distribuição ou modificação sem autorização prévia
            e por escrito.
          </p>

          <h2>6. Limitação de Responsabilidade</h2>
          <p>
            A Plataforma é fornecida "como está" e "conforme disponível".
            Na máxima extensão permitida pela lei, [Nome da Empresa] não se
            responsabiliza por danos indiretos, incidentais ou consequentes
            decorrentes do uso ou da impossibilidade de uso do serviço,
            incluindo indisponibilidades temporárias, falhas técnicas ou
            perda de dados.
          </p>

          <h2>7. Alterações nestes Termos</h2>
          <p>
            Podemos atualizar estes Termos periodicamente. Alterações
            relevantes serão comunicadas por e-mail ou aviso na Plataforma.
            O uso continuado após a alteração implica concordância com os
            novos termos.
          </p>

          <h2>8. Lei Aplicável e Foro</h2>
          <p>
            Estes Termos são regidos pelas leis da República Federativa do
            Brasil. Fica eleito o foro da comarca de [cidade/UF] para
            dirimir quaisquer controvérsias, com renúncia a qualquer outro,
            por mais privilegiado que seja.
          </p>

          <h2>9. Contato</h2>
          <p>
            Dúvidas sobre estes Termos podem ser enviadas para{' '}
            [e-mail de contato].
          </p>
        </section>

        {/* ---------------- POLÍTICA DE PRIVACIDADE ---------------- */}
        <section id="privacidade" className="legal-card">
          <span className="legal-badge">
            <span className="legal-badge__dot" /> LGPD
          </span>
          <h1 className="legal-card__title">Política de Privacidade</h1>
          <LegalUpdated slug="privacidade" />

          <p>
            Esta Política de Privacidade descreve como [Nome da Empresa]
            coleta, usa, armazena e protege os dados pessoais dos usuários
            da Plataforma, em conformidade com a Lei Geral de Proteção de
            Dados Pessoais (Lei nº 13.709/2018 — LGPD).
          </p>

          <h2>1. Dados que Coletamos</h2>
          <ul>
            <li><strong>Dados de cadastro:</strong> nome, CPF, e-mail e senha (armazenada de forma criptografada);</li>
            <li><strong>Dados de uso:</strong> registros de acesso (IP, data/hora, navegador) e interações com a Plataforma;</li>
            <li><strong>Dados fornecidos voluntariamente:</strong> informações enviadas em formulários, suporte ou comunicações.</li>
          </ul>

          <h2>2. Finalidade do Tratamento</h2>
          <p>Os dados coletados são utilizados para:</p>
          <ul>
            <li>Criar e gerenciar sua conta, incluindo autenticação e login;</li>
            <li>Prestar, manter e melhorar os serviços da Plataforma;</li>
            <li>Enviar comunicações relacionadas ao serviço (avisos, suporte, atualizações);</li>
            <li>Cumprir obrigações legais e regulatórias;</li>
            <li>Prevenir fraudes e garantir a segurança da Plataforma.</li>
          </ul>

          <h2>3. Base Legal (LGPD)</h2>
          <p>
            O tratamento dos dados pessoais é fundamentado, conforme o
            caso, na execução de contrato (art. 7º, V), no cumprimento de
            obrigação legal (art. 7º, II), no legítimo interesse (art. 7º,
            IX) ou no consentimento do titular (art. 7º, I), quando
            aplicável.
          </p>

          <h2>4. Compartilhamento de Dados</h2>
          <p>
            Não vendemos dados pessoais. Podemos compartilhar dados com:
          </p>
          <ul>
            <li>Provedores de infraestrutura e tecnologia que apoiam a operação da Plataforma (ex.: hospedagem, e-mail transacional), sob obrigações contratuais de confidencialidade;</li>
            <li>Autoridades públicas, quando exigido por lei ou ordem judicial.</li>
          </ul>

          <h2>5. Direitos do Titular</h2>
          <p>Nos termos da LGPD, você pode solicitar, a qualquer momento:</p>
          <ul>
            <li>Confirmação da existência de tratamento e acesso aos dados;</li>
            <li>Correção de dados incompletos, inexatos ou desatualizados;</li>
            <li>Anonimização, bloqueio ou eliminação de dados desnecessários ou excessivos;</li>
            <li>Portabilidade dos dados a outro fornecedor de serviço;</li>
            <li>Eliminação dos dados tratados com base no consentimento;</li>
            <li>Revogação do consentimento e informação sobre as consequências dessa revogação.</li>
          </ul>
          <p>
            Solicitações podem ser feitas pelo e-mail [e-mail do encarregado
            / DPO].
          </p>

          <h2>6. Segurança das Informações</h2>
          <p>
            Adotamos medidas técnicas e administrativas razoáveis para
            proteger os dados pessoais contra acessos não autorizados,
            perda, alteração ou destruição, incluindo criptografia de
            senhas e controles de acesso.
          </p>

          <h2>7. Retenção de Dados</h2>
          <p>
            Os dados são mantidos pelo tempo necessário para cumprir as
            finalidades descritas nesta Política ou conforme exigido por
            lei, sendo eliminados ou anonimizados após esse período.
          </p>

          <h2>8. Cookies</h2>
          <p>
            A Plataforma pode utilizar cookies e tecnologias semelhantes
            para autenticação, preferências e análise de uso. Você pode
            gerenciar essas preferências nas configurações do seu
            navegador.
          </p>

          <h2>9. Alterações nesta Política</h2>
          <p>
            Esta Política pode ser atualizada periodicamente. A versão mais
            recente estará sempre disponível nesta página, com a data da
            última atualização indicada no topo.
          </p>

          <h2>10. Encarregado de Dados (DPO) e Contato</h2>
          <p>
            Para exercer seus direitos ou esclarecer dúvidas sobre o
            tratamento de dados pessoais, entre em contato com nosso
            Encarregado de Proteção de Dados em [e-mail do DPO].
          </p>
        </section>
      </main>
    </div>
  )
}