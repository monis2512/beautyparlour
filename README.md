# My Parlour — Render-ready full-stack Angular + Java application

A responsive beauty salon enquiry application with an Angular public website, Spring Boot REST API, PostgreSQL, and protected admin dashboard.

## Render deployment — mobile friendly

This repository is configured for Render Blueprint deployment. You do **not** need to enter database credentials manually.

1. Upload the contents of this project to a new GitHub repository from the GitHub website.
2. In Render, choose **New → Blueprint** and select the repository.
3. Render reads `render.yaml`, creates the PostgreSQL database, builds Angular, packages it inside Spring Boot, and starts the application.
4. Render will ask you to provide only:
   - `ADMIN_USERNAME`
   - `ADMIN_PASSWORD`
5. Deploy. The public website and admin dashboard are served from the same URL.

### URLs after deployment
- Public website: `https://<your-render-service>.onrender.com/`
- Admin: `https://<your-render-service>.onrender.com/admin`

### Database
Production uses PostgreSQL automatically. Hibernate creates/updates the `customer_queries` table with `spring.jpa.hibernate.ddl-auto=update`.

### Local development
The project is primarily prepared for Render. For local use, set PostgreSQL variables or adapt `application.properties` to your local database.

## Admin features
- Login using Render environment variables
- Enquiry count
- Customer name, mobile, service and question
- Customer details displayed separately
- India/Kolkata timestamp
- Auto-refresh every 10 seconds
- One-tap phone call
- Delete enquiry
- Logout back to public page

## Customer questions
The form includes common visitor questions such as pricing, appointments, hairstyle selection, hair colour, bridal packages, facial selection, and home service.
