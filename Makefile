# Codewisdom Train-Ticket system

Repo=cqqcqq
Tag=response

# build image
.PHONY: build
build: clean-image package build-image

#.PHONY: build-image
#build-image:
#	@hack/build-image.sh

.PHONY: package
package:
	@mvn clean package -DskipTests

.PHONY: add-jacoco
add-jacoco:
	@hack/add-jacoco.sh

.PHONY: build-image
build-image:
	@hack/build-image.sh $(Repo) $(Tag)

# push image
.PHONY: push-image
push-image:
	@hack/push-image.sh $(Repo)

.PHONY: publish-image
publish-image:
	@script/publish-docker-images.sh $(Repo) $(Tag)

.PHONY: clean
clean:
	@mvn clean
	@hack/clean-image.sh $(Repo)

# clean image
.PHONY: clean-image
clean-image:
	@hack/clean-image.sh $(Repo)
