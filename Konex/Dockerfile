# Stage 0, based on Node.js, to build and compile Angular
FROM node:11-alpine as node

WORKDIR /app
COPY ./ /app/
RUN npm install
RUN npm rebuild node-sass
ARG configuration=production
RUN npm run build -- --prod --configuration=$configuration

# Stage 1, based on Nginx, to have only the compiled app, ready for production with Nginx
FROM nginx
COPY --from=node /app/dist/adpe-employee-front /usr/share/nginx/html
# Config router to SPA in NGINX
COPY ./nginx-custom.conf /etc/nginx/conf.d/default.conf
