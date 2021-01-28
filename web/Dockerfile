FROM node:13.12.0-alpine as builder

# install and cache app dependencies
COPY package.json package-lock.json ./

RUN npm install && mkdir /react-frontend && mv ./node_modules ./react-frontend

WORKDIR /react-frontend

COPY . .

RUN npm run build



# ------------------------------------------------------
# Production Build
# ------------------------------------------------------
FROM nginx:1.19.6-alpine
#RUN rm /etc/nginx/conf.d/default.conf
#COPY nginx/default.conf.template /etc/nginx/conf.d
ENV CREATE_API_URL=$CREATE_API_URL
COPY /nginx /etc/nginx/templates/
COPY --from=builder /react-frontend/build /usr/share/nginx/html

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
