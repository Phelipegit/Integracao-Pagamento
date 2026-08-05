import { useState } from 'react'
import { Link } from 'react-router-dom'
import '../css/Login.css'

/* ---------- helpers ---------- */
function onlyDigits(value) {
  return value.replace(/\D/g, '')
}

function isValidEmail(email) {
  return /^[^\s@]+@[^\s@]+\.[^\s@]{2,}$/.test(email)
}

function isValidCpf(cpf) {
  const digits = onlyDigits(cpf)
  if (digits.length !== 11) return false
  if (/^(\d)\1+$/.test(digits)) return false
  let sum = 0
  for (let i = 0; i < 9; i++) sum += Number(digits[i]) * (10 - i)
  let check1 = (sum * 10) % 11
  if (check1 === 10) check1 = 0
  if (check1 !== Number(digits[9])) return false
  sum = 0
  for (let i = 0; i < 10; i++) sum += Number(digits[i]) * (11 - i)
  let check2 = (sum * 10) % 11
  if (check2 === 10) check2 = 0
  return check2 === Number(digits[10])
}

function identify(value) {
  const trimmed = value.trim()
  if (!trimmed) return { valid: false, kind: '' }
  if (trimmed.includes('@')) {
    return { valid: isValidEmail(trimmed), kind: 'email' }
  }
  return { valid: isValidCpf(trimmed), kind: 'cpf' }
}

/* ---------- componente ---------- */
export default function Login() {
  const [identifier, setIdentifier] = useState('')
  const [password, setPassword] = useState('')
  const [touched, setTouched] = useState({ identifier: false, password: false })
  const [submitted, setSubmitted] = useState(false)
  const API_URL = "https://api.phelipedev.com.br/auth/login";
  const idCheck = identify(identifier)

  const identifierError =
    !touched.identifier && !submitted
      ? ''
      : !identifier.trim()
        ? 'Informe o seu CPF ou e-mail.'
        : !idCheck.valid
          ? 'CPF ou e-mail inválido.'
          : ''

  const passwordError =
    !touched.password && !submitted
      ? ''
      : !password
        ? 'Informe a sua senha.'
        : password.length < 8
          ? 'A senha precisa ter pelo menos 8 caracteres.'
          : ''

  function handleSubmit(event) {
    event.preventDefault()
    setSubmitted(true)
    setTouched({ identifier: true, password: true })
    if (identifierError || passwordError) {
        return;
    }

    const response = fetch('')
  }

  function handleIdentifierChange(event) {
    const raw = event.target.value
    if (raw.includes('@')) {
      setIdentifier(raw)
    } else {
      setIdentifier(onlyDigits(raw))
    }
  }

  let placeholder = 'CPF ou e-mail'
  if (identifier && !identifier.includes('@') && idCheck.kind === 'cpf') {
    placeholder = '000.000.000-00'
  } else if (identifier.includes('@')) {
    placeholder = 'voce@email.com'
  }

  return (
    <div className="page">
      <div className="bg-grid" aria-hidden="true" />
      <div className="bg-glow" aria-hidden="true" />

      <main className="shell">
        <section className="card">
          <h2 className="card__title">Entrar na sua conta</h2>

          <form className="form" onSubmit={handleSubmit} noValidate>
            <div className="field">
              <label htmlFor="identifier" className="field__label">
                CPF ou e-mail
              </label>
              <p className="field__sub">Você pode entrar com qualquer um dos dois.</p>
              <input
                id="identifier"
                name="identifier"
                type="text"
                inputMode="email"
                autoComplete="username"
                placeholder={placeholder}
                value={identifier}
                onChange={handleIdentifierChange}
                onBlur={() => setTouched((t) => ({ ...t, identifier: true }))}
                aria-invalid={!!identifierError}
                className={`input login-id ${identifierError ? 'input--err' : ''}`}
              />
              {identifierError && <p className="msg msg--err">{identifierError}</p>}
            </div>

            <div className="field">
              <label htmlFor="password" className="field__label">Senha</label>
              <input
                id="password"
                name="password"
                type="password"
                autoComplete="current-password"
                placeholder="••••••••••"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                onBlur={() => setTouched((t) => ({ ...t, password: true }))}
                aria-invalid={!!passwordError}
                className={`input ${passwordError ? 'input--err' : ''}`}
              />
              {passwordError && <p className="msg msg--err">{passwordError}</p>}
            </div>

            <button type="submit" className="btn">
              Entrar
              <svg viewBox="0 0 24 24" aria-hidden="true" className="btn__icon">
                <path d="M4 12h15M13 6l6 6-6 6" stroke="currentColor" strokeWidth="2" fill="none" />
              </svg>
            </button>

            <p className="login-link">
              Não tem conta? <Link to="/register">Criar conta</Link>
            </p>

            <p className="legal">
              Ao continuar você aceita os{' '}
              <a href="#termos">termos de uso</a> e a{' '}
              <a href="#privacidade">política de privacidade</a>.
            </p>
          </form>
        </section>
      </main>
    </div>
  )
}