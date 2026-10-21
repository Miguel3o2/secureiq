(() => {
  'use strict';

  const prefersReduced = window.matchMedia('(prefers-reduced-motion: reduce)').matches;
  const $ = (sel, ctx = document) => ctx.querySelector(sel);
  const $$ = (sel, ctx = document) => Array.from(ctx.querySelectorAll(sel));

  const head = $('#site-head');
  const onScroll = () => head.classList.toggle('scrolled', window.scrollY > 12);
  onScroll();
  window.addEventListener('scroll', onScroll, { passive: true });

  const menuBtn = $('#menu-btn');
  const nav = $('#nav');

  const closeNav = () => {
    nav.classList.remove('open');
    menuBtn.setAttribute('aria-expanded', 'false');
    menuBtn.setAttribute('aria-label', 'Open menu');
  };

  menuBtn.addEventListener('click', () => {
    const open = nav.classList.toggle('open');
    menuBtn.setAttribute('aria-expanded', String(open));
    menuBtn.setAttribute('aria-label', open ? 'Close menu' : 'Open menu');
  });

  $$('a', nav).forEach(a => a.addEventListener('click', closeNav));
  document.addEventListener('keydown', e => { if (e.key === 'Escape') closeNav(); });
  window.addEventListener('resize', () => { if (window.innerWidth > 900) closeNav(); });

  const revealEls = $$('.reveal');
  const qaMode = new URLSearchParams(location.search).has('qa');
  if (qaMode) document.documentElement.classList.add('qa');
  if (qaMode || prefersReduced || !('IntersectionObserver' in window)) {
    revealEls.forEach(el => el.classList.add('in'));
  } else {
    const revealIO = new IntersectionObserver((entries, obs) => {
      entries.forEach(entry => {
        if (entry.isIntersecting) {
          entry.target.classList.add('in');
          obs.unobserve(entry.target);
        }
      });
    }, { rootMargin: '0px 0px -12% 0px', threshold: 0.08 });
    revealEls.forEach(el => revealIO.observe(el));
  }

  const navLinks = $$('#nav a');
  const sections = navLinks
    .map(a => document.querySelector(a.getAttribute('href')))
    .filter(Boolean);

  if ('IntersectionObserver' in window && sections.length) {
    const spy = new IntersectionObserver(entries => {
      entries.forEach(entry => {
        if (!entry.isIntersecting) return;
        const id = entry.target.id;
        navLinks.forEach(a => a.classList.toggle('active', a.getAttribute('href') === '#' + id));
      });
    }, { rootMargin: '-42% 0px -52% 0px' });
    sections.forEach(s => spy.observe(s));
  }

  const fills = $$('.risk-fill');
  const fillBars = () => fills.forEach(f => {
    const w = f.dataset.w || '0';
    setTimeout(() => { f.style.width = w + '%'; }, prefersReduced || qaMode ? 0 : 380);
  });
  if (qaMode) {
    fillBars();
    window.addEventListener('load', () => {
      const vw = document.documentElement.clientWidth;
      const wide = $$('body *')
        .map(el => ({ el, r: el.getBoundingClientRect() }))
        .filter(x => x.r.right > vw + 1 || x.r.width > vw + 1)
        .slice(0, 14)
        .map(x => `${x.el.tagName}.${(x.el.className || '').toString().split(' ')[0]} w=${Math.round(x.r.width)} r=${Math.round(x.r.right)}`);
      document.body.dataset.qa = JSON.stringify({ vw, sw: document.documentElement.scrollWidth, wide });
    });
  } else if ('IntersectionObserver' in window) {
    const barIO = new IntersectionObserver((entries, obs) => {
      if (entries.some(e => e.isIntersecting)) { fillBars(); obs.disconnect(); }
    }, { threshold: 0.3 });
    if (fills[0]) barIO.observe(fills[0]);
  } else {
    fillBars();
  }

  const counters = $$('[data-count]');
  const runCount = el => {
    const target = Number(el.dataset.count);
    if (prefersReduced) { el.textContent = target.toLocaleString(); return; }
    const dur = 1400;
    const t0 = performance.now();
    const tick = now => {
      const p = Math.min((now - t0) / dur, 1);
      const eased = 1 - Math.pow(1 - p, 3);
      el.textContent = Math.round(target * eased).toLocaleString();
      if (p < 1) requestAnimationFrame(tick);
    };
    requestAnimationFrame(tick);
  };
  counters.forEach(runCount);

  const feed = $('#mini-feed');
  const feedCount = $('#mini-count');
  const samples = [
    ['CRIT', 'sev-critical', 'After-hours badge — Server Room'],
    ['HIGH', 'sev-high', 'Tailgate detected — Main Entrance'],
    ['MED', 'sev-medium', 'Door held open &gt;30s — Loading Bay'],
    ['HIGH', 'sev-high', 'Motion w/o access — CCTV Room'],
    ['MED', 'sev-medium', 'Off-pattern sequence — Server Room'],
    ['LOW', 'sev-low', 'Badge valid — Stairwell C'],
    ['MED', 'sev-medium', 'Visitor badge expired — Main Lobby'],
    ['HIGH', 'sev-high', 'Failed auth ×3 — Executive Floor']
  ];
  let ingested = 1248;
  let sampleIndex = 0;

  const addFeedRow = () => {
    if (!feed) return;
    const [sev, cls, text] = samples[sampleIndex % samples.length];
    sampleIndex++;
    const d = new Date();
    const time = [d.getHours(), d.getMinutes()].map(n => String(n).padStart(2, '0')).join(':');
    const row = document.createElement('div');
    row.className = 'feed-mini-row new';
    row.innerHTML = `<span class="ev-time">${time}</span><span class="sev ${cls}">${sev}</span><span>${text}</span>`;
    feed.prepend(row);
    while (feed.children.length > 4) feed.removeChild(feed.lastElementChild);
    ingested += Math.floor(Math.random() * 12) + 3;
    if (feedCount) feedCount.textContent = ingested.toLocaleString() + ' ingested';
  };

  if (!prefersReduced && feed) {
    setInterval(addFeedRow, 3800);
  }

  const form = $('#contact-form');
  const status = $('#form-status');
  if (form) {
    form.addEventListener('submit', e => {
      e.preventDefault();
      const name = $('#name').value.trim();
      const email = $('#email').value.trim();
      const message = $('#message').value.trim();
      const validEmail = /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);

      if (!name || !validEmail || !message) {
        status.textContent = 'Please add your name, a valid email and a short brief.';
        status.style.color = 'var(--amber)';
        (!name ? $('#name') : !validEmail ? $('#email') : $('#message')).focus();
        return;
      }

      status.style.color = 'var(--lime)';
      status.textContent = `Thanks ${name.split(' ')[0]} — brief received. We reply within one business day.`;
      form.reset();
    });
  }

  const year = $('#year');
  if (year) year.textContent = new Date().getFullYear();
})();
